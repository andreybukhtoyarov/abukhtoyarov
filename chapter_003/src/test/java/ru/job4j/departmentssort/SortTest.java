package ru.job4j.departmentssort;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class SortTest {
    private final String[] departments = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1",
            "K1/SK1/SSK2", "K2", "K2/SK1/SSK2", "K2/SK1/SSK1"
    };

    @Test
    public void whenDepartmentsAscendingSortThenAscendingSort() {
        Sort sort = new Sort();
        String[] expected = {"K1", "K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2",
                "K2", "K2/SK1", "K2/SK1/SSK1", "K2/SK1/SSK2"
        };
        assertThat(sort.ascendingSort(this.departments), is(expected));
    }

    @Test
    public void whenAddDepartmentsThenAddToDepartmentsK1AndK2SK1() {
        Sort sort = new Sort();
        String[] expected = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2",
                "K2", "K2/SK1/SSK2", "K2/SK1/SSK1", "K1", "K2/SK1"
        };
        assertThat(sort.addDepartments(this.departments), is(expected));
    }

    @Test
    public void whenDepartmentsDescendingSortThenDescendingSort() {
        Sort sort = new Sort();
        String[] expected = {"K2", "K2/SK1", "K2/SK1/SSK2", "K2/SK1/SSK1",
                "K1", "K1/SK2", "K1/SK1", "K1/SK1/SSK2", "K1/SK1/SSK1"
        };
        assertThat(sort.descendingSort(this.departments), is(expected));
    }
}
