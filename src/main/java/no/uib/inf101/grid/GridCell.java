package no.uib.inf101.grid;


/**
 *  A GridCell contains a Cellposition "pos" and a genereic E component "value"
 *
 * @param pos   the position
 * @param value     the value
 */
public record GridCell<E>(CellPosition pos, E value) {

    public void setValue(E newValue) {
        new GridCell<>(this.pos, newValue);
    }
}
