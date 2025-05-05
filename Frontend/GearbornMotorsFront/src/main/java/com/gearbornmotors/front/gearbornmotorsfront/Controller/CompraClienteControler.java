package com.gearbornmotors.front.gearbornmotorsfront.Controller;

import com.gearbornmotors.front.gearbornmotorsfront.Dto.Cliente.ClienteDto;

public class CompraClienteControler {

    private ClienteDto cliente;

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
        // Aquí puedes actualizar la interfaz con los datos del cliente
        // por ejemplo: nombreLabel.setText(cliente.getNombre());
    }

}
