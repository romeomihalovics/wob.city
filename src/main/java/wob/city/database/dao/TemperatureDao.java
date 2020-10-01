package wob.city.database.dao;

import wob.city.database.dao.abstraction.Dao;
import wob.city.database.dto.QueryDto;
import wob.city.database.dto.RecordTemperatureDto;
import wob.city.database.dto.TemperatureReportDto;

public class TemperatureDao implements Dao {
    public void uploadTemperatureReport(TemperatureReportDto temperatureReport) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `temperature_report` (`city`, `part_of_day`, `temperature`, `date`, `season`) VALUES (?, ?, ?, ?, ?)");

        queryDto.addParam(temperatureReport.getCity());
        queryDto.addParam(temperatureReport.getPartOfDay());
        queryDto.addParam(temperatureReport.getTemperature());
        queryDto.addParam(temperatureReport.getDate());
        queryDto.addParam(temperatureReport.getSeason());

        runQuery(queryDto);
    }

    public void uploadRecordTemperature(RecordTemperatureDto recordTemperature) {
        QueryDto queryDto = new QueryDto();

        queryDto.setQuery("INSERT INTO `record_temperature` (`city`, `type`, `temperature`, `date`, `season`) VALUES (?, ?, ?, ?, ?)");

        queryDto.addParam(recordTemperature.getCity());
        queryDto.addParam(recordTemperature.getType().name());
        queryDto.addParam(recordTemperature.getTemperature());
        queryDto.addParam(recordTemperature.getDate());
        queryDto.addParam(recordTemperature.getSeason());

        runQuery(queryDto);
    }
}
