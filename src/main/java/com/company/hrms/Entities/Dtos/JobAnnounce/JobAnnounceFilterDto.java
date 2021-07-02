package com.company.hrms.Entities.Dtos.JobAnnounce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAnnounceFilterDto {

    private List<Integer> cityId;
    private List<Integer> jobTypeId;
    private int Page;
    private int Size;

}
