package model.gigilhome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Entity;
import utils.Const;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Home extends Entity {
    private Long capacity = Const.START_HOME_CAPACITY;
    private Long updateIncrease = Const.START_HOME_UPDATE_INCREASE;
    private Long numOfFloor = Const.START_NUMBER_FLOOR;
    private Long unit = Const.START_NUMBER_UNITS;
    private Long gilgPerUnit = Const.GILGE_PER_UNIT;

    @Override
    public void update() {

    }
}
