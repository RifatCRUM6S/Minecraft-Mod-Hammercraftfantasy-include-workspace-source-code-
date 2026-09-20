/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.hammercraftfantasy.client.model.*;

@EventBusSubscriber(Dist.CLIENT)
public class HammercraftfantasyModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelchaos_spawn.LAYER_LOCATION, Modelchaos_spawn::createBodyLayer);
		event.registerLayerDefinition(Modelrotfly.LAYER_LOCATION, Modelrotfly::createBodyLayer);
		event.registerLayerDefinition(Modelvalkiasspear.LAYER_LOCATION, Modelvalkiasspear::createBodyLayer);
		event.registerLayerDefinition(ModelDemonez2.LAYER_LOCATION, ModelDemonez2::createBodyLayer);
		event.registerLayerDefinition(ModelRage_and_fury.LAYER_LOCATION, ModelRage_and_fury::createBodyLayer);
		event.registerLayerDefinition(Modelforsaken.LAYER_LOCATION, Modelforsaken::createBodyLayer);
		event.registerLayerDefinition(Modeltzaangor_halberd.LAYER_LOCATION, Modeltzaangor_halberd::createBodyLayer);
		event.registerLayerDefinition(Modeldemonsteel_armor.LAYER_LOCATION, Modeldemonsteel_armor::createBodyLayer);
		event.registerLayerDefinition(Modelring_of_lust.LAYER_LOCATION, Modelring_of_lust::createBodyLayer);
		event.registerLayerDefinition(Modelplaguezombie.LAYER_LOCATION, Modelplaguezombie::createBodyLayer);
		event.registerLayerDefinition(Modelobsession_and_desire.LAYER_LOCATION, Modelobsession_and_desire::createBodyLayer);
		event.registerLayerDefinition(Modeleric_spawn.LAYER_LOCATION, Modeleric_spawn::createBodyLayer);
		event.registerLayerDefinition(Modelsigvald.LAYER_LOCATION, Modelsigvald::createBodyLayer);
		event.registerLayerDefinition(Modeleric.LAYER_LOCATION, Modeleric::createBodyLayer);
		event.registerLayerDefinition(Modelt_champions_armor.LAYER_LOCATION, Modelt_champions_armor::createBodyLayer);
		event.registerLayerDefinition(Modelpink_horror.LAYER_LOCATION, Modelpink_horror::createBodyLayer);
		event.registerLayerDefinition(Modelcorruption_and_rot.LAYER_LOCATION, Modelcorruption_and_rot::createBodyLayer);
		event.registerLayerDefinition(Modelmaggotlord.LAYER_LOCATION, Modelmaggotlord::createBodyLayer);
		event.registerLayerDefinition(Modeltamurkhan.LAYER_LOCATION, Modeltamurkhan::createBodyLayer);
		event.registerLayerDefinition(Modelk_cultist.LAYER_LOCATION, Modelk_cultist::createBodyLayer);
		event.registerLayerDefinition(Modeldeamonette.LAYER_LOCATION, Modeldeamonette::createBodyLayer);
		event.registerLayerDefinition(ModelPlaguebearer.LAYER_LOCATION, ModelPlaguebearer::createBodyLayer);
		event.registerLayerDefinition(Modelsilverguard.LAYER_LOCATION, Modelsilverguard::createBodyLayer);
		event.registerLayerDefinition(Modeldeceit_and_scheme.LAYER_LOCATION, Modeldeceit_and_scheme::createBodyLayer);
		event.registerLayerDefinition(Modelk_champions_armor.LAYER_LOCATION, Modelk_champions_armor::createBodyLayer);
		event.registerLayerDefinition(Modeln_champions_armor.LAYER_LOCATION, Modeln_champions_armor::createBodyLayer);
		event.registerLayerDefinition(Models_champions_armor.LAYER_LOCATION, Models_champions_armor::createBodyLayer);
		event.registerLayerDefinition(Modelscreamer.LAYER_LOCATION, Modelscreamer::createBodyLayer);
		event.registerLayerDefinition(Modelfiend_of_slaanesh.LAYER_LOCATION, Modelfiend_of_slaanesh::createBodyLayer);
		event.registerLayerDefinition(Modelflamers.LAYER_LOCATION, Modelflamers::createBodyLayer);
		event.registerLayerDefinition(Modelnagoxo.LAYER_LOCATION, Modelnagoxo::createBodyLayer);
		event.registerLayerDefinition(Modelrot_maggot.LAYER_LOCATION, Modelrot_maggot::createBodyLayer);
		event.registerLayerDefinition(Modeltzaangor.LAYER_LOCATION, Modeltzaangor::createBodyLayer);
		event.registerLayerDefinition(ModelBloodletter1.LAYER_LOCATION, ModelBloodletter1::createBodyLayer);
		event.registerLayerDefinition(Modelgreat_unclean_one.LAYER_LOCATION, Modelgreat_unclean_one::createBodyLayer);
		event.registerLayerDefinition(Modelflamers_head.LAYER_LOCATION, Modelflamers_head::createBodyLayer);
		event.registerLayerDefinition(Modelflesh_hounds.LAYER_LOCATION, Modelflesh_hounds::createBodyLayer);
		event.registerLayerDefinition(ModelBlue_horror.LAYER_LOCATION, ModelBlue_horror::createBodyLayer);
		event.registerLayerDefinition(Modelk_chaoswarrior.LAYER_LOCATION, Modelk_chaoswarrior::createBodyLayer);
		event.registerLayerDefinition(Modelsteed_of_slaanesh.LAYER_LOCATION, Modelsteed_of_slaanesh::createBodyLayer);
		event.registerLayerDefinition(Modelvalkia.LAYER_LOCATION, Modelvalkia::createBodyLayer);
		event.registerLayerDefinition(ModelNurgling1.LAYER_LOCATION, ModelNurgling1::createBodyLayer);
		event.registerLayerDefinition(Modelskarbrand.LAYER_LOCATION, Modelskarbrand::createBodyLayer);
	}
}