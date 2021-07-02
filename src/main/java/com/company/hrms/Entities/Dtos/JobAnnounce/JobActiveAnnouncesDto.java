package com.company.hrms.Entities.Dtos.JobAnnounce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobActiveAnnouncesDto {

    private int id;

    //Joinlenecek
    private String companyName;

    //Joinlenecek
    private String jobPosition;

    private int positionCount;

    private LocalDate announceDate;

    private LocalDate lastDateOfAppeal;
}
