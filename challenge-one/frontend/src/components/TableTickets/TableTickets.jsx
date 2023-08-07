import './TableTickets.css';
import React, { useState } from 'react';


const TableTickets = ({tickets, onVisualize, onClean, onEdit, onDelete}) => {
  const [month, setMonth] = useState(0);
  const [year, setYear] = useState(0);

  const formatarData = (dataString) => {
    const [ano, mes, dia] = dataString.split('-');
    return `${dia}/${mes}/${ano}`;
  }

  return (
    <div>
      <div className='filters'>
        <div>
          <label htmlFor="Month" className='form-label'>Mês: </label>
          <select 
            id="Month"
            onChange={(e) => setMonth(e.target.value)}
            value={month}
          >
            <option value="0">Selecionar</option>
            <option value="1">Janeiro</option>
            <option value="2">Fevereiro</option>
            <option value="3">Março</option>
            <option value="4">Abril</option>
            <option value="5">Maio</option>
            <option value="6">Junho</option>
            <option value="7">Julho</option>
            <option value="8">Agosto</option>
            <option value="9">Setembro</option>
            <option value="10">Outubro</option>
            <option value="11">Novembro</option>
            <option value="12">Dezembro</option>
          </select>
        </div>
        <div>
          <label htmlFor="Year" className='form-label'>Ano: </label>
          <select 
            id="Year"
            onChange={(e) => setYear(e.target.value)}
            value={year}
          >
            <option value="0">Selecionar</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
            <option value="2019">2019</option>
            <option value="2020">2020</option>
            <option value="2021">2021</option>
            <option value="2022">2022</option>
            <option value="2023">2023</option>
          </select>
        </div>
        <button className='btn btn-primary' onClick={onVisualize(month, year)}>Visualizar</button>
        <button className='btn btn-secondary' onClick={() => {setMonth(0); setYear(0)}}>Limpar filtros</button>
      </div>
      <table className='table table-bordered table-hover table-striped'>
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Cliente</th>
            <th>Data de Abertura</th>
            <th>Data de Encerramento</th>
            <th>Módulo</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {tickets.map((ticket) => (
            <tr key={ticket.id}>
              <td>{ticket.id}</td>
              <td>{ticket.title}</td>
              <td>{ticket.client.name}</td>
              <td>{formatarData(ticket.dateOpen)}</td>
              <td>{formatarData(ticket.dateClose)}</td>
              <td>{ticket.module.name}</td>
              <td>
                <button className='btn btn-warning' onClick={() => onEdit(ticket)}>edit</button>
                <button className='btn btn-danger' onClick={() => onDelete(ticket.id)}>del</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TableTickets;
