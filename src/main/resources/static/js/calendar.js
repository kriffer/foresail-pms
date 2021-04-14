$(document).ready(
    function () {

        var unavailableDates = [
            { start: '2020-07-11', end: '2020-07-15' },

            { start: '2020-07-22', end: '2020-07-23' },

            { start: '2020-08-01', end: '2020-08-07' }

        ];


        $('#calendar').availabilityCalendar(unavailableDates);

    });