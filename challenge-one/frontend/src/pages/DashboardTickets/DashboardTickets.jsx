import './DashboardTickets.css'
import React, { useState, useEffect } from 'react'
import TicketDashboard from '../../components/DoughnutChart/DoughnutChart'
import TableTickets from '../../components/TableTickets/TableTickets'
import Modal from '../../components/Modal/Modal'
import { getClients, getModules, getTickets, createTicket, updateTicket, deleteTicket } from '../../services/AxiosService'

const DashboardTickets = () => {

    const [clients, setClients] = useState([])
    const [modules, setModules] = useState([])
    const [tickets, setTickets] = useState([])
    const [ticket, setTicket] = useState({})
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

        if (ticket.id) {
            updateTicket(ticket)
                .then(res => {
                    setTicket({});
                    closeModal();
                })
                .catch(err => {
                    console.log(err)
                    closeModal();
                })
        } else {
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
    }

    // melhorar essa função
    function closeModal() {
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

    function onVisualize(month, year) {
        getTickets(month, year)
            .then(res => {
                setTickets(res.data.tickets);
                const { contadorPorCliente, contadorPorModulo } = contarTicketsPorClienteEModulo(res.data);
                setTicketsByClient(contadorPorCliente);
                setTicketsByModule(contadorPorModulo);
            })
            .catch(err => console.log(err))
    }

    function setClientInTicket(e) {
        const client = clients.find(client => client.id === parseInt(e.target.value))
        setTicket({ ...ticket, client: client })
    }

    function setModuleInTicket(e) {
        let module = modules.find(module => module.id === parseInt(e.target.value))
        setTicket({ ...ticket, module: module })
    }

    function renderModal() {
        return (
            <Modal
                    isOpen={isOpenModal}
                    onClose={closeModal}
                    >
                    <h2 className='text-center'>{ticket.id?'Editar':'Novo'} chamado</h2>
                    <div className="form">
                        <div className="row">
                            <div className="col">
                                <div className='form-group'>
                                    <label className='form-label'>Título</label>
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
                            <div className="col">
                                <div className='form-group'>
                                    <label className='form-label'>Cliente</label>
                                    <select 
                                    className="form-select"
                                    name="client" 
                                    onChange={(e) => setClientInTicket(e)}
                                    value={ticket.client ? ticket.client.id : ''}
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
                            <div className="col">
                                <div className='form-group'>
                                    <label className='form-label'>Módulo</label>
                                    <select 
                                    className="form-select"
                                    name="module"
                                    onChange={(e) => setModuleInTicket(e)}
                                    value={ticket.module ? ticket.module.id : ''}
                                    >
                                    <option value="">Selecione...</option>
                                        {modules.map((module) => (
                                            <option key={module.id} value={module.id}>
                                                {module.name}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                            <div className="col">
                                <div className="form-group">
                                    <label className='form-label'>Data de Abertura</label>
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
                                    <label className='form-label'>Data de fechamento</label>
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
                            <div className="d-flex justify-content-center">
                                <button className="btn btn-primary" onClick={() => saveTicket(ticket)}>
                                    Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </Modal>
        )
    }

    function renderTable() {
        return (
            <TableTickets 
            tickets={tickets} 
            onVisualize={onVisualize}
            onDelete={onDelete}
            onEdit={onEdit}
            />
        )
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
                <button className='btn btn-primary' onClick={() => setIsOpenModal(true)}>Novo Chamado</button>
                {renderModal()}
            </div>


            {/*
                Componente abaixo é utilizado para exibir os chamados em uma tabela
            */}
            <div className="table-tickets">
                {renderTable()}
            </div>
        </div>
    )
}

export default DashboardTickets;