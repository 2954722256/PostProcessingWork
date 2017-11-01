package jet.opengl.demos.intel.assao;

interface ASSAO_Macro {

	int SSAO_SAMPLERS_SLOT0 = 0;
	int SSAO_SAMPLERS_SLOT1 = 1;
	int SSAO_SAMPLERS_SLOT2 = 2;
	int SSAO_SAMPLERS_SLOT3 = 3;
	int SSAO_NORMALMAP_OUT_UAV_SLOT = 4;
	int SSAO_CONSTANTS_BUFFERSLOT = 0;
	int SSAO_TEXTURE_SLOT0 = 0;
	int SSAO_TEXTURE_SLOT1 = 1;
	int SSAO_TEXTURE_SLOT2 = 2;
	int SSAO_TEXTURE_SLOT3 = 3;
	int SSAO_TEXTURE_SLOT4 = 4;
	int SSAO_LOAD_COUNTER_UAV_SLOT = 4;
	int SSAO_MAX_TAPS = 32;
	int SSAO_MAX_REF_TAPS = 512;
	int SSAO_ADAPTIVE_TAP_BASE_COUNT = 5;
	int SSAO_ADAPTIVE_TAP_FLEXIBLE_COUNT = (SSAO_MAX_TAPS-SSAO_ADAPTIVE_TAP_BASE_COUNT);
	int SSAO_DEPTH_MIP_LEVELS = 4;
	int SSAO_ENABLE_NORMAL_WORLD_TO_VIEW_CONVERSION = 0;
}
