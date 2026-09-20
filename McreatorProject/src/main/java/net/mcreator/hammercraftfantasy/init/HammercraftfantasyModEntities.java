/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.hammercraftfantasy.entity.*;
import net.mcreator.hammercraftfantasy.HammercraftfantasyMod;

@EventBusSubscriber
public class HammercraftfantasyModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, HammercraftfantasyMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<SkarbrandEntity>> SKARBRAND = register("skarbrand",
			EntityType.Builder.<SkarbrandEntity>of(SkarbrandEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).fireImmune()

					.sized(3f, 7f));
	public static final DeferredHolder<EntityType<?>, EntityType<BloodletterEntity>> BLOODLETTER = register("bloodletter",
			EntityType.Builder.<BloodletterEntity>of(BloodletterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<UnknowBeautyEntity>> UNKNOW_BEAUTY = register("unknow_beauty",
			EntityType.Builder.<UnknowBeautyEntity>of(UnknowBeautyEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.ridingOffset(-0.6f).sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<DemonezEntity>> DEMONEZ = register("demonez",
			EntityType.Builder.<DemonezEntity>of(DemonezEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<HeraldofKhorneEntity>> HERALDOF_KHORNE = register("heraldof_khorne",
			EntityType.Builder.<HeraldofKhorneEntity>of(HeraldofKhorneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<NurglingEntity>> NURGLING = register("nurgling",
			EntityType.Builder.<NurglingEntity>of(NurglingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 0.9f));
	public static final DeferredHolder<EntityType<?>, EntityType<PinkHorrorEntity>> PINK_HORROR = register("pink_horror",
			EntityType.Builder.<PinkHorrorEntity>of(PinkHorrorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BlueHorrorEntity>> BLUE_HORROR = register("blue_horror",
			EntityType.Builder.<BlueHorrorEntity>of(BlueHorrorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<PinkHorrorArrowEntity>> PINK_HORROR_ARROW = register("pink_horror_arrow",
			EntityType.Builder.<PinkHorrorArrowEntity>of(PinkHorrorArrowEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<FleshhoundsEntity>> FLESHHOUNDS = register("fleshhounds",
			EntityType.Builder.<FleshhoundsEntity>of(FleshhoundsEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(1f, 1.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<KchaoswarriorEntity>> KCHAOSWARRIOR = register("kchaoswarrior",
			EntityType.Builder.<KchaoswarriorEntity>of(KchaoswarriorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.8f, 2.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<KcultistEntity>> KCULTIST = register("kcultist",
			EntityType.Builder.<KcultistEntity>of(KcultistEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<RotflyEntity>> ROTFLY = register("rotfly",
			EntityType.Builder.<RotflyEntity>of(RotflyEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.8f, 2.4f));
	public static final DeferredHolder<EntityType<?>, EntityType<PlaguebearerEntity>> PLAGUEBEARER = register("plaguebearer",
			EntityType.Builder.<PlaguebearerEntity>of(PlaguebearerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.8f, 2.3f));
	public static final DeferredHolder<EntityType<?>, EntityType<GreatuncleanoneEntity>> GREATUNCLEANONE = register("greatuncleanone",
			EntityType.Builder.<GreatuncleanoneEntity>of(GreatuncleanoneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(4f, 5f));
	public static final DeferredHolder<EntityType<?>, EntityType<RotmaggotEntity>> ROTMAGGOT = register("rotmaggot",
			EntityType.Builder.<RotmaggotEntity>of(RotmaggotEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.1f, 0.1f));
	public static final DeferredHolder<EntityType<?>, EntityType<PlaguezombieEntity>> PLAGUEZOMBIE = register("plaguezombie",
			EntityType.Builder.<PlaguezombieEntity>of(PlaguezombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<NagoxoEntity>> NAGOXO = register("nagoxo",
			EntityType.Builder.<NagoxoEntity>of(NagoxoEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.5f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ChaosspawnEntity>> CHAOSSPAWN = register("chaosspawn",
			EntityType.Builder.<ChaosspawnEntity>of(ChaosspawnEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.5f, 3f));
	public static final DeferredHolder<EntityType<?>, EntityType<ForsakenEntity>> FORSAKEN = register("forsaken",
			EntityType.Builder.<ForsakenEntity>of(ForsakenEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.7f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<FiendofslaaneshEntity>> FIENDOFSLAANESH = register("fiendofslaanesh",
			EntityType.Builder.<FiendofslaaneshEntity>of(FiendofslaaneshEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.8f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<DeamonetteEntity>> DEAMONETTE = register("deamonette",
			EntityType.Builder.<DeamonetteEntity>of(DeamonetteEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<SteedofslaaneshEntity>> STEEDOFSLAANESH = register("steedofslaanesh",
			EntityType.Builder.<SteedofslaaneshEntity>of(SteedofslaaneshEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.9f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<TamurkhanEntity>> TAMURKHAN = register("tamurkhan",
			EntityType.Builder.<TamurkhanEntity>of(TamurkhanEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(2.1f, 3.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ValkiaEntity>> VALKIA = register("valkia",
			EntityType.Builder.<ValkiaEntity>of(ValkiaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(0.6f, 2.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ValkiasspearEntity>> VALKIASSPEAR = register("valkiasspear",
			EntityType.Builder.<ValkiasspearEntity>of(ValkiasspearEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final DeferredHolder<EntityType<?>, EntityType<SigvaldEntity>> SIGVALD = register("sigvald",
			EntityType.Builder.<SigvaldEntity>of(SigvaldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<SilverguardEntity>> SILVERGUARD = register("silverguard",
			EntityType.Builder.<SilverguardEntity>of(SilverguardEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<ScreamerEntity>> SCREAMER = register("screamer",
			EntityType.Builder.<ScreamerEntity>of(ScreamerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(2.2f, 0.2f));
	public static final DeferredHolder<EntityType<?>, EntityType<FlamerEntity>> FLAMER = register("flamer",
			EntityType.Builder.<FlamerEntity>of(FlamerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 3f));
	public static final DeferredHolder<EntityType<?>, EntityType<TzaangorEntity>> TZAANGOR = register("tzaangor",
			EntityType.Builder.<TzaangorEntity>of(TzaangorEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.8f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<EricEntity>> ERIC = register("eric",
			EntityType.Builder.<EricEntity>of(EricEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<EricspawnEntity>> ERICSPAWN = register("ericspawn",
			EntityType.Builder.<EricspawnEntity>of(EricspawnEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune()

					.sized(3f, 5f));
	public static final DeferredHolder<EntityType<?>, EntityType<MaggotlordEntity>> MAGGOTLORD = register("maggotlord",
			EntityType.Builder.<MaggotlordEntity>of(MaggotlordEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1f));
	public static final DeferredHolder<EntityType<?>, EntityType<TzaangorhalberdEntity>> TZAANGORHALBERD = register("tzaangorhalberd",
			EntityType.Builder.<TzaangorhalberdEntity>of(TzaangorhalberdEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		SkarbrandEntity.init(event);
		BloodletterEntity.init(event);
		UnknowBeautyEntity.init(event);
		DemonezEntity.init(event);
		HeraldofKhorneEntity.init(event);
		NurglingEntity.init(event);
		PinkHorrorEntity.init(event);
		BlueHorrorEntity.init(event);
		FleshhoundsEntity.init(event);
		KchaoswarriorEntity.init(event);
		KcultistEntity.init(event);
		RotflyEntity.init(event);
		PlaguebearerEntity.init(event);
		GreatuncleanoneEntity.init(event);
		RotmaggotEntity.init(event);
		PlaguezombieEntity.init(event);
		NagoxoEntity.init(event);
		ChaosspawnEntity.init(event);
		ForsakenEntity.init(event);
		FiendofslaaneshEntity.init(event);
		DeamonetteEntity.init(event);
		SteedofslaaneshEntity.init(event);
		TamurkhanEntity.init(event);
		ValkiaEntity.init(event);
		SigvaldEntity.init(event);
		SilverguardEntity.init(event);
		ScreamerEntity.init(event);
		FlamerEntity.init(event);
		TzaangorEntity.init(event);
		EricEntity.init(event);
		EricspawnEntity.init(event);
		MaggotlordEntity.init(event);
		TzaangorhalberdEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(SKARBRAND.get(), SkarbrandEntity.createAttributes().build());
		event.put(BLOODLETTER.get(), BloodletterEntity.createAttributes().build());
		event.put(UNKNOW_BEAUTY.get(), UnknowBeautyEntity.createAttributes().build());
		event.put(DEMONEZ.get(), DemonezEntity.createAttributes().build());
		event.put(HERALDOF_KHORNE.get(), HeraldofKhorneEntity.createAttributes().build());
		event.put(NURGLING.get(), NurglingEntity.createAttributes().build());
		event.put(PINK_HORROR.get(), PinkHorrorEntity.createAttributes().build());
		event.put(BLUE_HORROR.get(), BlueHorrorEntity.createAttributes().build());
		event.put(FLESHHOUNDS.get(), FleshhoundsEntity.createAttributes().build());
		event.put(KCHAOSWARRIOR.get(), KchaoswarriorEntity.createAttributes().build());
		event.put(KCULTIST.get(), KcultistEntity.createAttributes().build());
		event.put(ROTFLY.get(), RotflyEntity.createAttributes().build());
		event.put(PLAGUEBEARER.get(), PlaguebearerEntity.createAttributes().build());
		event.put(GREATUNCLEANONE.get(), GreatuncleanoneEntity.createAttributes().build());
		event.put(ROTMAGGOT.get(), RotmaggotEntity.createAttributes().build());
		event.put(PLAGUEZOMBIE.get(), PlaguezombieEntity.createAttributes().build());
		event.put(NAGOXO.get(), NagoxoEntity.createAttributes().build());
		event.put(CHAOSSPAWN.get(), ChaosspawnEntity.createAttributes().build());
		event.put(FORSAKEN.get(), ForsakenEntity.createAttributes().build());
		event.put(FIENDOFSLAANESH.get(), FiendofslaaneshEntity.createAttributes().build());
		event.put(DEAMONETTE.get(), DeamonetteEntity.createAttributes().build());
		event.put(STEEDOFSLAANESH.get(), SteedofslaaneshEntity.createAttributes().build());
		event.put(TAMURKHAN.get(), TamurkhanEntity.createAttributes().build());
		event.put(VALKIA.get(), ValkiaEntity.createAttributes().build());
		event.put(SIGVALD.get(), SigvaldEntity.createAttributes().build());
		event.put(SILVERGUARD.get(), SilverguardEntity.createAttributes().build());
		event.put(SCREAMER.get(), ScreamerEntity.createAttributes().build());
		event.put(FLAMER.get(), FlamerEntity.createAttributes().build());
		event.put(TZAANGOR.get(), TzaangorEntity.createAttributes().build());
		event.put(ERIC.get(), EricEntity.createAttributes().build());
		event.put(ERICSPAWN.get(), EricspawnEntity.createAttributes().build());
		event.put(MAGGOTLORD.get(), MaggotlordEntity.createAttributes().build());
		event.put(TZAANGORHALBERD.get(), TzaangorhalberdEntity.createAttributes().build());
	}
}