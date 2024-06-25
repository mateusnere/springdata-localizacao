package com.github.mateusnere.localizacao.domain.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.github.mateusnere.localizacao.domain.entity.Cidade;

public abstract class CidadeSpec {
    
    public static Specification<Cidade> idEqual(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, cb) -> cb.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(root.get("nome"), nome);
    }

    public static Specification<Cidade> nomeLikeUpper(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Cidade> habitantesGreaterThan(Long qtdHabitantes) {
        return (root, query, cb) -> cb.greaterThan(root.get("habitantes"), qtdHabitantes);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max) {
        return (root, query, cb) -> cb.between(root.get("habitantes"), min, max);
    }


}
