package com.bosonit.block7crudvalidation.persona.repository;


import com.bosonit.block7crudvalidation.persona.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//Para vincular la implementacion con la interface hay que llamarla igual y ponerle Impl al final, así, Spring buscará en el paquete del repositorio esta clase
public class IPersonaRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Persona> getPersonasCustomQuery(HashMap<String, Object> conditions, int pageNumber, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) ->
        {
            switch (field) {
                case "userPersona":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "createdDate":
                    String fechaOrder = (String) conditions.get("fechaOrder");
                    if (fechaOrder.equals("sup"))
                        predicates.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                    else if (fechaOrder.equals("equal"))
                        predicates.add(cb.equal(root.<Date>get(field), (Date) value));
                    else
                        predicates.add(cb.lessThan(root.<Date>get(field), (Date) value));
                    break;
            }
        });
        String order = (String) conditions.get("order");
        if (order.equals("name") || order.equals("userPersona")) {
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(cb.asc(root.get(order)));
        } else {
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        }

        return entityManager.createQuery(query).setMaxResults(pageSize).setFirstResult(pageNumber*pageSize).getResultList();
    }
}
