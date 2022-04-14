let dataMoney = {};
let labels = [];
const url = 'http://localhost:8080/admin/booked-tour/month'

const fetchApi = async () => {
    const dataResponse = await fetch(url).then(function(response) {
        return response.json();
    }).then(function(data) {
        return data;
    }).catch(function() {
        console.log("Error");
    });
    console.log(dataResponse);
    const data = {
        labels: dataResponse['label'],
        datasets: [{
            label: 'Revenue(VND)',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: dataResponse['data'],
        }]
    };

    const config = {
        type: 'line',
        data: data,
        options: {}
    };

    const myChart = new Chart(
        document.getElementById('myChart'),
        config
    );
}

fetchApi();


// const dataResponse = fetch(url).then(function(response) {
//         return response.json();
//     }).then(function(data) {
//         return data;
//     }).catch(function() {
//         console.log("Error");
//     });
// console.log(dataResponse);

