package artAuction;

import dataStructures.Comparator;

public class WorkCompare implements Comparator<WorkInformation> {

    @Override
    public int compare(WorkInformation element1, WorkInformation element2) {
        int result =  element2.getHighestSalePrice() -
                element1.getHighestSalePrice();
        if (result == 0)
            result = element1.getName().compareTo(element2.getName());
        return result;
    }
}
