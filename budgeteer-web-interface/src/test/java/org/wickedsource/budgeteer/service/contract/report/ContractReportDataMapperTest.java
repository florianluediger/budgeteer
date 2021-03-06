package org.wickedsource.budgeteer.service.contract.report;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wickedsource.budgeteer.IntegrationTestTemplate;
import org.wickedsource.budgeteer.persistence.contract.ContractEntity;
import org.wickedsource.budgeteer.persistence.contract.ContractRepository;

import java.util.Date;

public class ContractReportDataMapperTest extends IntegrationTestTemplate {


    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractReportDataMapper testSubject;

    @Test
    @DatabaseSetup("contractMapperTest.xml")
    @DatabaseTearDown(value = "contractMapperTest.xml", type = DatabaseOperation.DELETE_ALL)
    public void whenTaxrateIsNull() {
        ContractEntity contractEntity = contractRepository.findOne(3L);
        ContractReportData contractBaseData = testSubject.map(contractEntity, new Date());
        Assertions.assertThat(contractBaseData.getTaxRate()).isCloseTo(0.00, Percentage.withPercentage(10e-8));
    }

    @Test
    @DatabaseSetup("contractMapperTest.xml")
    @DatabaseTearDown(value = "contractMapperTest.xml", type = DatabaseOperation.DELETE_ALL)
    public void whenTaxrateIsNotNull() {
        ContractEntity contractEntity = contractRepository.findOne(4L);
        ContractReportData contractBaseData = testSubject.map(contractEntity, new Date());
        Assertions.assertThat(contractBaseData.getTaxRate()).isCloseTo(1.00, Percentage.withPercentage(10e-8));
    }
}