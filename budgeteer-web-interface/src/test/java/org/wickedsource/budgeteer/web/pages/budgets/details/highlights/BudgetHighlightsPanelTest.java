package org.wickedsource.budgeteer.web.pages.budgets.details.highlights;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.wickedsource.budgeteer.service.MoneyUtil;
import org.wickedsource.budgeteer.service.budget.BudgetDetailData;
import org.wickedsource.budgeteer.service.budget.BudgetService;
import org.wickedsource.budgeteer.web.AbstractWebTestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import static org.mockito.Mockito.when;

public class BudgetHighlightsPanelTest extends AbstractWebTestTemplate {

    @Autowired
    private BudgetService service;

    private Random random = new Random();

    @Test
    public void render() {
        when(service.loadBudgetDetailData(1l)).thenReturn(createDetailData());
        WicketTester tester = getTester();
        BudgetHighlightsModel model = new BudgetHighlightsModel(1l);
        BudgetHighlightsPanel panel = new BudgetHighlightsPanel("panel", model);
        tester.startComponentInPage(panel);

        tester.assertContains("Budget 1");
    }

    public BudgetDetailData createDetailData() {
        BudgetDetailData data = new BudgetDetailData();
        data.setLastUpdated(new Date());
        data.setName("Budget 1");
        data.setSpent(MoneyUtil.createMoneyFromCents(random.nextInt(5000)));
        data.setTotal(MoneyUtil.createMoneyFromCents(random.nextInt(5000)));
        data.setTags(Arrays.asList("Active"));
        return data;
    }
}
