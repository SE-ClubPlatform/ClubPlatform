package SW_Engineering.Group3.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainResult<T> {

    private int count;
    private T data;

    public MainResult(int count, T data){
        this.count = count;
        this.data = data;
    }

}
