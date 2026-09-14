package over.rental.manager.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemApiControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ItemService itemService;

    @Test
    @Transactional
    void getItemApiTest() throws Exception {
        ItemForm form = new ItemForm();
        form.setName("카메라");
        form.setDescription("테스트 카메라");

        Item savedItem = itemService.createItem(form);
        mockMvc.perform(get("/api/items/{id}", savedItem.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedItem.getId()))
                .andExpect(jsonPath("$.name").value("카메라"))
                .andExpect(jsonPath("$.description").value("테스트 카메라"))
                .andExpect(jsonPath("$.rentalStatus").value(savedItem.getRentalStatus().name()));
    }

    @Test
    @Transactional
    void itemNotFoundApiTest() throws Exception {
        mockMvc.perform(get("/api/items/{id}", 999999L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("물건을 찾을 수 없습니다."));
    }
}
