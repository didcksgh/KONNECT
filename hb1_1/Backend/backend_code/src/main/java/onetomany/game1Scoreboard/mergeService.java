package onetomany.game1Scoreboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class mergeService {
    @Autowired
    private game1ScoreboardRepository userRepository;


    public static void mergeSort(List<game1User> userList, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(userList, left, mid);
            mergeSort(userList, mid + 1, right);
            merge(userList, left, mid, right);
        }
    }

    public static void merge(List<game1User> userList, int left, int mid, int right) {
        List<game1User> temp = new ArrayList<>();
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (userList.get(i).getScore() <= userList.get(j).getScore()) {
                temp.add(userList.get(i));
                i++;
            } else {
                temp.add(userList.get(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.add(userList.get(i));
            i++;
        }

        while (j <= right) {
            temp.add(userList.get(j));
            j++;
        }

        for (int k = left; k <= right; k++) {
            userList.set(k, temp.get(k - left));
        }
    }


}
