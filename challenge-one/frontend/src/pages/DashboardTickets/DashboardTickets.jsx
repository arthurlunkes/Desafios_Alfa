import './DashboardTickets.css'
import React, { useState, useEffect } from 'react'
import TicketDashboard from '../../components/DoughnutChart/DoughnutChart'
import TableTickets from '../../components/TableTickets/TableTickets'
import Modal from '../../components/Modal/Modal'
import { getClients, getModules, getTickets, createTicket, updateTicket, deleteTicket } from '../../services/AxiosService'

export default function DashboardTickets() {

    const [clients, setClients] = useState([])
    const [modules, setModules] = useState([])
    const [tickets, setTickets] = useState([])
    const [ticket, setTicket] = useState({})
    const [filter, setFilter] = useState({})
    const [isOpenModal, setIsOpenModal] = useState(false)
    const [ticketsByClient, setTicketsByClient] = useState({});
    const [ticketsByModule, setTicketsByModule] = useState({});

    useEffect(() => {
        getClients()
            .then(res => setClients(res.data))
            .catch(err => console.log(err))

        getModules()
            .then(res => setModules(res.data))
            .catch(err => console.log(err))

        getTickets()
            .then(res => {
                setTickets(res.data.tickets);
                const { contadorPorCliente, contadorPorModulo } = contarTicketsPorClienteEModulo(res.data);
                setTicketsByClient(contadorPorCliente);
                setTicketsByModule(contadorPorModulo);
            })
            .catch(err => console.log(err))
    }, [])


    function contarTicketsPorClienteEModulo(data) {
        const contadorPorCliente = {};
        const contadorPorModulo = {};

        // Percorre os tickets agrupados por cliente
        for (const clienteTickets of Object.values(data.ticketsByClient)) {
            for (const ticket of clienteTickets) {
                const clienteNome = ticket.client.name;
                contadorPorCliente[clienteNome] = (contadorPorCliente[clienteNome] || 0) + 1;
            }
        }

        // Percorre os tickets agrupados por módulo
        for (const moduloTickets of Object.values(data.ticketsByModule)) {
            for (const ticket of moduloTickets) {
                const moduloNome = ticket.module.name;
                contadorPorModulo[moduloNome] = (contadorPorModulo[moduloNome] || 0) + 1;
            }
        }

        return { contadorPorCliente, contadorPorModulo };
    }

    // melhorar essa função
    function saveTicket(ticket) {
        setIsOpenModal(false);
        createTicket(ticket)
            .then(res => {
                setTicket({});
                closeModal();
            })
            .catch(err => {
                console.log(err)
                closeModal();
            })
    }

    // melhorar essa função
    function closeModal() {
        console.log(ticket);
        setIsOpenModal(false);
        setTicket({});
    }

    function openModal() {
        setIsOpenModal(true);
    }

    function onEdit(ticket) {
        setTicket(ticket);
        openModal();
    }

    function onDelete(id) {
        deleteTicket(id)
            .then(res => {
                
            })
            .catch(err => {
                console.log(err)
            })
    }

    return (
        <div className="dashboard">

            <header><h1>Dashboard dos Chamados</h1></header>

            {/*
                Componente abaixo é utilizado para exibir os gráficos dos chamados
            */}
            <div className="charts">
                <TicketDashboard title="Quantidade por Cliente" data={ticketsByClient} />
                <TicketDashboard title="Quantidade por Módulo" data={ticketsByModule} />
            </div>

            {/* 
                Componente abaixo é utilizado para exibir um formulário para criar um novo chamado 
            */}
            <div className='create-ticket'>
                <button className='btn btn-primary' onClick={() => setIsOpenModal(true)}>Criar Chamado</button>
                <Modal
                    isOpen={isOpenModal}
                    onClose={closeModal}
                    onSave={saveTicket}>
                    <h2 className='text-center'>Criar chamado</h2>
                    <div className="form">
                        <div className="text-center">
                        </div>
                        <div className="row">
                            <div className="col-12 col-md-6">
                                <div className="form-group">
                                    <label>Título</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        name="title"
                                        placeholder="Digite o título"
                                        value={ticket.title}
                                        onChange={(e) => setTicket({ ...ticket, title: e.target.value })}
                                    />
                                </div>
                            </div>
                            <div className="col-12 col-md-6">
                                <div className="form-group">
                                    <label>Cliente</label>
                                    <select name="client" 
                                    onChange={(e) => setTicket({ ...ticket, client: e.target.value })}
                                    value={ticket.client}
                                    >
                                        <option value="">Selecione...</option>
                                        {clients.map((client) => (
                                            <option key={client.id} value={client.id} >
                                                {client.name}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12 col-md-6">
                                <div className="form-group">
                                    <label>Módulo</label>
                                    <select name="module" id="">
                                        {modules.map((module) => (
                                            <option key={module.id} value={module.id}>
                                                {module.name}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="col-12 col-md-6">
                                <div className="form-group">
                                    <label>Data de Abertura</label>
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="dateOpen"
                                        value={ticket.dateOpen}
                                        onChange={(e) => setTicket({ ...ticket, dateOpen: e.target.value })}
                                    />
                                </div>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-12 col-md-6">
                                <div className="form-group">
                                    <label>Data de fechamento</label>
                                    <input
                                        type="date"
                                        className="form-control"
                                        name="dateClose"
                                        value={ticket.dateClose}
                                        onChange={(e) => setTicket({ ...ticket, dateClose: e.target.value })}
                                    />
                                </div>
                            </div>
                        </div>
                        <hr />
                        <div className="row">
                            <div className="col-12 d-flex justify-content-center">
                                <button className="btn btn-primary" onClick={() => saveTicket(ticket)}>
                                    Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </Modal>
            </div>


            {/*
                Componente abaixo é utilizado para exibir os chamados em uma tabela
            */}
            <div className="table-tickets">
                <TableTickets 
                tickets={tickets} 
                onDelete={onDelete}
                onEdit={onEdit}
                />
            </div>
        </div>
    )

}