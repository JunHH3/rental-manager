package over.rental.manager.item;

public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private RentalStatus rentalStatus;

    public ItemDto(Long id, String name, String description, RentalStatus rentalStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rentalStatus = rentalStatus;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public static ItemDto from(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getRentalStatus()
        );
    }
}
