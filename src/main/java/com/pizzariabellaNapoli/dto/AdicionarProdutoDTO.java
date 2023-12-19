package com.pizzariabellaNapoli.dto;

/**
 * Description of AdicionarProdutoDTO
 * Created by calle on 20/12/2023.
 */
public class AdicionarProdutoDTO {

    private Long idProduto;
    private int quantidade;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
