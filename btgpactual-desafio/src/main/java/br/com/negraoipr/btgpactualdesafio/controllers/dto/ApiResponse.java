package br.com.negraoipr.btgpactualdesafio.controllers.dto;

import java.util.List;

public record ApiResponse<T>(
        List<T> data,
        PaginationResponse pagination

) {
}
