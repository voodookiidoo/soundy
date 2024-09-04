package com.soundy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Slf4j
public class JpaTest {
//	@Autowired
//	PilotRepository pilotRepository;
//	@Autowired
//	PilotWeaponRepository weaponRepository;
//	@Autowired
//	TitanRepository titanRepository;
//
//
//	@Test
//	public void inits() {
//		assertThat(pilotRepository).isNotNull();
//		assertThat(weaponRepository).isNotNull();
//		assertThat(titanRepository).isNotNull();
//	}
//
//	@Test
//	void createTest() {
//		long amountBefore = pilotRepository.count();
//		PilotEntity entity = new PilotEntity();
//		entity.setName("Megakiller");
//		pilotRepository.save(entity);
//		long amountAfter = pilotRepository.count();
//		assertThat(amountAfter).isEqualTo(amountBefore + 1);
//
//	}
//
//	@Test
//	public void titanCreateTest() {
//		TitanEntity entity = new TitanEntity();
//	}
//
//	@Test
//	public void indexTest() {
//		assertThat(pilotRepository.findAll()).isNotNull();
//		assertThat(weaponRepository.findAll()).isNotNull();
//		assertThat(titanRepository.findAll()).isNotNull();
//	}
//
//	@Test
//	public void updateTest() {
//		String name = "Hayato";
//		PilotEntity target = pilotRepository.findById(4).get();
//		target.setName(name);
//		pilotRepository.save(target);
//		PilotEntity testEntity = pilotRepository.findById(4).get();
//		assertThat(target.getName()).isEqualTo(testEntity.getName());
//	}
//
//	@Test
//	public void deleteTest() {
//		int someId = pilotRepository.findAll(
//				PageRequest.ofSize(1)
//		).get().findFirst().get().getId();
//		// а в котлине было бы короче!
//		// ищем любую сущность которая поппадётся нам первой
//		// и берём её айдишник
//		assertThat(pilotRepository.removeById(someId)).isEqualTo(1);
//		// проверка на то что количество изменённых строк в таблице == 1
//		assertThat(pilotRepository.findById(someId).orElse(null)).isNull();
//		// проверка что сущность более не существует в таблице
//	}
}
