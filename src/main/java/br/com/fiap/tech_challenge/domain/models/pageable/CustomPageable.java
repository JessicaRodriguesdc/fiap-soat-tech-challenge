package br.com.fiap.tech_challenge.domain.models.pageable;

import java.util.List;

public record CustomPageable<T>(List<T> content, CustomPage page) {

}
