import React from 'react';
import './DoughnutChart.css'
import { Doughnut } from 'react-chartjs-2';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, Title } from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend, Title);

function DoughnutChart(props) {

    const data = {
        labels: Object.keys(props.data),
        datasets: [{
            data: Object.values(props.data),
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)',
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
            ],
            borderWidth: 1,
            hoverOffset: 35
        }]
    }

    const options = {
        responsive: true, // Permite redimensionar o gráfico
        maintainAspectRatio: false, // Mantém a proporção do gráfico com o contêiner
        plugins: {
            title: {
                display: true, // Exibir o título
                text: props.title, // Texto do título
                font: {
                    size: 14, // Tamanho do título
                },
            },
            legend: {
                position: 'right', // Posição das opções ('top', 'right', 'bottom', 'left')
            }
        },
        layout: {
            padding: 10, // Espaçamento em todas as direções
        },
    }

    return (
        <div className="chart">
            <Doughnut
                data={data}
                options={options}
            />
        </div>
    );
}

export default DoughnutChart;