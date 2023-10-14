package Model;

import java.awt.Color;

class Map {
	
	private Continent[] continents;
	public Continent[] getContinents() {
		return continents;
	}
	
	// tava como private 
	public Map() {
	}
	
	public static Map mapGenerator() {

		Map map = new Map();
		
		map.continents = new Continent[6];
		
		Territory africaDoSul = new Territory("Africa do Sul", new Coordinates(577f/1024, 568f/768), new Coordinates[] { new Coordinates(0.515625f, 0.704427f), new Coordinates(0.543945f, 0.774740f), new Coordinates(0.583008f, 0.774740f), new Coordinates(0.590820f, 0.755208f), new Coordinates(0.598633f, 0.753906f), new Coordinates(0.611328f, 0.721354f), new Coordinates(0.602539f, 0.704427f) });		
		Territory angola = new Territory("Angola", new Coordinates(551f/1024, 507f/768), new Coordinates[] { new Coordinates(0.507812f, 0.621094f), new Coordinates(0.573242f, 0.621094f), new Coordinates(0.584961f, 0.648438f), new Coordinates(0.559570f, 0.703125f), new Coordinates(0.515625f, 0.703125f), new Coordinates(0.522461f, 0.684896f), new Coordinates(0.501953f, 0.635417f) });
		Territory argelia = new Territory("Argelia", new Coordinates(463f/1024, 375f/768), new Coordinates[] { new Coordinates(0.425781f, 0.432292f), new Coordinates(0.460938f, 0.432292f), new Coordinates(0.467773f, 0.449219f), new Coordinates(0.480469f, 0.449219f), new Coordinates(0.487305f, 0.467448f), new Coordinates(0.526367f, 0.467448f), new Coordinates(0.500000f, 0.527344f), new Coordinates(0.415039f, 0.527344f), new Coordinates(0.399414f, 0.494792f) });
		Territory egito = new Territory("Egito", new Coordinates(565f/1024, 390f/768), new Coordinates[] { new Coordinates(0.500977f, 0.527344f), new Coordinates(0.534180f, 0.527344f), new Coordinates(0.548828f, 0.561198f), new Coordinates(0.607422f, 0.561198f), new Coordinates(0.579102f, 0.494792f), new Coordinates(0.583008f, 0.485677f), new Coordinates(0.574219f, 0.462240f), new Coordinates(0.529297f, 0.462240f) });
		Territory nigeria = new Territory("Nigeria", new Coordinates(511f/1024, 435f/768), new Coordinates[] { new Coordinates(0.500977f, 0.527344f), new Coordinates(0.534180f, 0.527344f), new Coordinates(0.573242f, 0.621094f), new Coordinates(0.506836f, 0.621094f), new Coordinates(0.491211f, 0.582031f), new Coordinates(0.437500f, 0.582031f), new Coordinates(0.415039f, 0.527344f) });
		Territory somalia = new Territory("Somalia", new Coordinates(618f/1024, 442f/768), new Coordinates[] { new Coordinates(0.549805f, 0.562500f), new Coordinates(0.584961f, 0.647135f), new Coordinates(0.559570f, 0.703125f), new Coordinates(0.602539f, 0.703125f), new Coordinates(0.626953f, 0.647135f), new Coordinates(0.637695f, 0.647135f), new Coordinates(0.656250f, 0.598958f), new Coordinates(0.624023f, 0.598958f), new Coordinates(0.608398f, 0.561198f) });
		
		Territory alasca = new Territory("Alasca", new Coordinates(82f/1024, 130f/768), new Coordinates[] { new Coordinates(0.083008f, 0.154948f), new Coordinates(0.070312f, 0.178385f), new Coordinates(0.056641f, 0.210938f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.129883f, 0.154948f) });
		Territory calgary = new Territory("Calgary", new Coordinates(174f/1024, 142f/768), new Coordinates[] { new Coordinates(0.130859f, 0.154948f), new Coordinates(0.123047f, 0.175781f), new Coordinates(0.142578f, 0.223958f), new Coordinates(0.217773f, 0.223958f), new Coordinates(0.225586f, 0.205729f), new Coordinates(0.239258f, 0.205729f), new Coordinates(0.251953f, 0.174479f), new Coordinates(0.276367f, 0.174479f), new Coordinates(0.264648f, 0.145833f), new Coordinates(0.235352f, 0.145833f), new Coordinates(0.224609f, 0.171875f), new Coordinates(0.207031f, 0.171875f), new Coordinates(0.203125f, 0.162760f), new Coordinates(0.147461f, 0.161458f), new Coordinates(0.145508f, 0.154948f) });
		Territory california = new Territory("California", new Coordinates(110f/1024, 265f/768), new Coordinates[] { new Coordinates(0.103516f, 0.279948f), new Coordinates(0.082031f, 0.329427f), new Coordinates(0.086914f, 0.342448f), new Coordinates(0.075195f, 0.369792f), new Coordinates(0.087891f, 0.397135f), new Coordinates(0.123047f, 0.395833f), new Coordinates(0.172852f, 0.279948f) });
		Territory groelandia = new Territory("Groenlandia", new Coordinates(305f/1024, 108f/768), new Coordinates[] { new Coordinates(0.264648f, 0.145833f), new Coordinates(0.278320f, 0.117188f), new Coordinates(0.362305f, 0.117188f), new Coordinates(0.370117f, 0.131510f), new Coordinates(0.370117f, 0.136719f), new Coordinates(0.362305f, 0.151042f), new Coordinates(0.356445f, 0.152344f), new Coordinates(0.342773f, 0.187500f), new Coordinates(0.326172f, 0.187500f), new Coordinates(0.320312f, 0.174479f), new Coordinates(0.276367f, 0.173177f) });
		Territory mexico =  new Territory("Mexico", new Coordinates(138f/1024, 354f/768), new Coordinates[] { new Coordinates(0.100586f, 0.459635f), new Coordinates(0.107422f, 0.444010f), new Coordinates(0.099609f, 0.423177f), new Coordinates(0.102539f, 0.416667f), new Coordinates(0.132812f, 0.484375f), new Coordinates(0.141602f, 0.484375f), new Coordinates(0.157227f, 0.524740f), new Coordinates(0.166992f, 0.524740f), new Coordinates(0.174805f, 0.540365f), new Coordinates(0.183594f, 0.520833f), new Coordinates(0.178711f, 0.511719f), new Coordinates(0.173828f, 0.510417f), new Coordinates(0.166992f, 0.498698f), new Coordinates(0.170898f, 0.493490f), new Coordinates(0.162109f, 0.475260f), new Coordinates(0.166992f, 0.462240f), new Coordinates(0.160156f, 0.446615f), new Coordinates(0.157227f, 0.454427f), new Coordinates(0.147461f, 0.455729f), new Coordinates(0.123047f, 0.397135f), new Coordinates(0.087891f, 0.397135f), new Coordinates(0.080078f, 0.414062f) });
		Territory novaYork = new Territory("Nova Iorque", new Coordinates(203f/1024, 266f/768), new Coordinates[] { new Coordinates(0.162109f, 0.397135f), new Coordinates(0.176758f, 0.397135f), new Coordinates(0.199219f, 0.447917f), new Coordinates(0.206055f, 0.432292f), new Coordinates(0.195312f, 0.408854f), new Coordinates(0.202148f, 0.391927f), new Coordinates(0.209961f, 0.391927f), new Coordinates(0.224609f, 0.356771f), new Coordinates(0.231445f, 0.356771f), new Coordinates(0.237305f, 0.338542f), new Coordinates(0.254883f, 0.338542f), new Coordinates(0.271484f, 0.295573f), new Coordinates(0.283203f, 0.294271f), new Coordinates(0.289062f, 0.279948f), new Coordinates(0.239258f, 0.279948f), new Coordinates(0.225586f, 0.316406f), new Coordinates(0.196289f, 0.316406f) });
		Territory quebec = new Territory("Quebec", new Coordinates(270f/1024, 196f/768), new Coordinates[] { new Coordinates(0.208984f, 0.278646f), new Coordinates(0.289062f, 0.279948f), new Coordinates(0.295898f, 0.263021f), new Coordinates(0.307617f, 0.263021f), new Coordinates(0.304688f, 0.273438f), new Coordinates(0.317383f, 0.272135f), new Coordinates(0.321289f, 0.260417f), new Coordinates(0.314453f, 0.242188f), new Coordinates(0.323242f, 0.223958f), new Coordinates(0.328125f, 0.233073f), new Coordinates(0.334961f, 0.217448f), new Coordinates(0.332031f, 0.205729f), new Coordinates(0.312500f, 0.205729f), new Coordinates(0.310547f, 0.201823f), new Coordinates(0.281250f, 0.201823f), new Coordinates(0.275391f, 0.217448f), new Coordinates(0.265625f, 0.217448f), new Coordinates(0.255859f, 0.240885f), new Coordinates(0.225586f, 0.242188f) });
		Territory texas = new Territory("Texas", new Coordinates(164f/1024, 264f/768), new Coordinates[] { new Coordinates(0.123047f, 0.397135f), new Coordinates(0.141602f, 0.438802f), new Coordinates(0.196289f, 0.316406f), new Coordinates(0.225586f, 0.315104f), new Coordinates(0.239258f, 0.279948f), new Coordinates(0.173828f, 0.278646f) });
		Territory vancouver = new Territory("Vancouver", new Coordinates(144f/1024, 188f/768), new Coordinates[] { new Coordinates(0.122070f, 0.175781f), new Coordinates(0.108398f, 0.210938f), new Coordinates(0.116211f, 0.230469f), new Coordinates(0.098633f, 0.269531f), new Coordinates(0.104492f, 0.279948f), new Coordinates(0.208008f, 0.279948f), new Coordinates(0.225586f, 0.242188f), new Coordinates(0.217773f, 0.223958f), new Coordinates(0.142578f, 0.223958f) });
		
		Territory arabiaSaudita = new Territory("Arabia Saudita", new Coordinates(671f/1024, 400f/768), new Coordinates[] { new Coordinates(0.637695f, 0.441406f), new Coordinates(0.664062f, 0.498698f), new Coordinates(0.699219f, 0.497396f), new Coordinates(0.708984f, 0.518229f), new Coordinates(0.682617f, 0.579427f), new Coordinates(0.632812f, 0.579427f), new Coordinates(0.624023f, 0.559896f), new Coordinates(0.630859f, 0.545573f), new Coordinates(0.612305f, 0.498698f) });		
		Territory bangladesh = new Territory("Bangladesh", new Coordinates(844f/1024, 380f/768), new Coordinates[] { new Coordinates(0.827148f, 0.449219f), new Coordinates(0.864258f, 0.449219f), new Coordinates(0.837891f, 0.507812f), new Coordinates(0.859375f, 0.557292f), new Coordinates(0.850586f, 0.571615f), new Coordinates(0.858398f, 0.589844f), new Coordinates(0.849609f, 0.605469f), new Coordinates(0.834961f, 0.567708f), new Coordinates(0.838867f, 0.555990f), new Coordinates(0.828125f, 0.528646f), new Coordinates(0.822266f, 0.528646f), new Coordinates(0.808594f, 0.494792f) });
		Territory cazaquistao = new Territory("Cazaquistao", new Coordinates(825f/1024, 219f/768), new Coordinates[] { new Coordinates(0.720703f, 0.256510f), new Coordinates(0.707031f, 0.283854f), new Coordinates(0.753906f, 0.285156f), new Coordinates(0.764648f, 0.313802f), new Coordinates(0.883789f, 0.313802f), new Coordinates(0.897461f, 0.282552f), new Coordinates(0.885742f, 0.255208f) });
		Territory china = new Territory("China", new Coordinates(791f/1024, 291f/768), new Coordinates[] { new Coordinates(0.785156f, 0.315104f), new Coordinates(0.801758f, 0.354167f), new Coordinates(0.853516f, 0.355469f), new Coordinates(0.866211f, 0.385417f), new Coordinates(0.818359f, 0.386719f), new Coordinates(0.792969f, 0.447917f), new Coordinates(0.766602f, 0.449219f), new Coordinates(0.736328f, 0.380208f), new Coordinates(0.764648f, 0.313802f) });
		Territory coreiaDoNorte = new Territory("Coreia do Norte", new Coordinates(862f/1024, 308f/768), new Coordinates[] { new Coordinates(0.818359f, 0.386719f), new Coordinates(0.867188f, 0.386719f), new Coordinates(0.872070f, 0.401042f), new Coordinates(0.886719f, 0.402344f), new Coordinates(0.891602f, 0.416667f), new Coordinates(0.805664f, 0.417969f) });
		Territory coreiaDoSul = new Territory("Coreia do Sul", new Coordinates(866f/1024, 333f/768), new Coordinates[] { new Coordinates(0.805664f, 0.417969f), new Coordinates(0.791992f, 0.447917f), new Coordinates(0.893555f, 0.449219f), new Coordinates(0.900391f, 0.432292f), new Coordinates(0.892578f, 0.417969f) });
		Territory estonia = new Territory("Estonia", new Coordinates(674f/1024, 132f/768), new Coordinates[] { new Coordinates(0.569336f, 0.119792f), new Coordinates(0.612305f, 0.217448f), new Coordinates(0.690430f, 0.218750f), new Coordinates(0.717773f, 0.154948f), new Coordinates(0.642578f, 0.154948f), new Coordinates(0.634766f, 0.180990f), new Coordinates(0.614258f, 0.180990f), new Coordinates(0.599609f, 0.144531f), new Coordinates(0.612305f, 0.145833f), new Coordinates(0.616211f, 0.154948f), new Coordinates(0.628906f, 0.154948f), new Coordinates(0.617188f, 0.125000f), new Coordinates(0.592773f, 0.125000f), new Coordinates(0.589844f, 0.118490f) });
		Territory india = new Territory("India", new Coordinates(794f/1024, 381f/768), new Coordinates[] { new Coordinates(0.766602f, 0.449219f), new Coordinates(0.827148f, 0.449219f), new Coordinates(0.788086f, 0.540365f), new Coordinates(0.792969f, 0.550781f), new Coordinates(0.778320f, 0.578125f), new Coordinates(0.746094f, 0.497396f) });
		Territory ira = new Territory("Ira", new Coordinates(717f/1024, 342f/768), new Coordinates[] { new Coordinates(0.683594f, 0.398438f), new Coordinates(0.699219f, 0.398438f), new Coordinates(0.673828f, 0.421875f), new Coordinates(0.699219f, 0.483073f), new Coordinates(0.715820f, 0.483073f), new Coordinates(0.722656f, 0.497396f), new Coordinates(0.732422f, 0.475260f) });
		Territory iraque = new Territory("Iraque", new Coordinates(673f/1024, 374f/768), new Coordinates[] { new Coordinates(0.637695f, 0.440104f), new Coordinates(0.664062f, 0.497396f), new Coordinates(0.689453f, 0.497396f), new Coordinates(0.676758f, 0.466146f), new Coordinates(0.685547f, 0.449219f), new Coordinates(0.673828f, 0.421875f), new Coordinates(0.683594f, 0.397135f), new Coordinates(0.655273f, 0.397135f) });
		Territory japao = new Territory("Japao", new Coordinates(949f/1024, 274f/768), new Coordinates[] { new Coordinates(0.915039f, 0.283854f), new Coordinates(0.933594f, 0.324219f), new Coordinates(0.930664f, 0.329427f), new Coordinates(0.941406f, 0.352865f), new Coordinates(0.931641f, 0.375000f), new Coordinates(0.923828f, 0.375000f), new Coordinates(0.916992f, 0.393229f), new Coordinates(0.899414f, 0.393229f), new Coordinates(0.908203f, 0.375000f), new Coordinates(0.905273f, 0.368490f), new Coordinates(0.911133f, 0.356771f), new Coordinates(0.916016f, 0.360677f), new Coordinates(0.920898f, 0.343750f), new Coordinates(0.905273f, 0.304688f) });
		Territory jordania = new Territory("Jordania", new Coordinates(625f/1024, 345f/768), new Coordinates[] { new Coordinates(0.654297f, 0.398438f), new Coordinates(0.612305f, 0.498698f), new Coordinates(0.607422f, 0.485677f), new Coordinates(0.597656f, 0.485677f), new Coordinates(0.587891f, 0.458333f), new Coordinates(0.600586f, 0.432292f), new Coordinates(0.618164f, 0.432292f), new Coordinates(0.632812f, 0.397135f) });
		Territory letonia = new Territory("Letonia", new Coordinates(659f/1024, 192f/768), new Coordinates[] { new Coordinates(0.595703f, 0.182292f), new Coordinates(0.577148f, 0.221354f), new Coordinates(0.569336f, 0.222656f), new Coordinates(0.595703f, 0.285156f), new Coordinates(0.708008f, 0.283854f), new Coordinates(0.720703f, 0.255208f), new Coordinates(0.708008f, 0.220052f), new Coordinates(0.610352f, 0.218750f) });
		Territory mongolia = new Territory("Mongolia", new Coordinates(856f/1024, 255f/768), new Coordinates[] { new Coordinates(0.786133f, 0.313802f), new Coordinates(0.801758f, 0.354167f), new Coordinates(0.873047f, 0.355469f), new Coordinates(0.883789f, 0.384115f), new Coordinates(0.893555f, 0.368490f), new Coordinates(0.881836f, 0.337240f), new Coordinates(0.887695f, 0.324219f), new Coordinates(0.883789f, 0.315104f) });
		Territory paquistao = new Territory("Paquistao", new Coordinates(748f/1024, 326f/768), new Coordinates[] { new Coordinates(0.700195f, 0.355469f), new Coordinates(0.745117f, 0.355469f), new Coordinates(0.736328f, 0.380208f), new Coordinates(0.766602f, 0.449219f), new Coordinates(0.745117f, 0.497396f), new Coordinates(0.722656f, 0.497396f), new Coordinates(0.732422f, 0.475260f), new Coordinates(0.691406f, 0.380208f) });
		Territory russia = new Territory("Russia", new Coordinates(779f/1024, 158f/768), new Coordinates[] { new Coordinates(0.689453f, 0.218750f), new Coordinates(0.707031f, 0.218750f), new Coordinates(0.720703f, 0.255208f), new Coordinates(0.805664f, 0.255208f), new Coordinates(0.848633f, 0.156250f), new Coordinates(0.755859f, 0.154948f), new Coordinates(0.724609f, 0.162760f), new Coordinates(0.729492f, 0.162760f), new Coordinates(0.725586f, 0.170573f), new Coordinates(0.710938f, 0.170573f) });
		Territory siberia = new Territory("Siberia", new Coordinates(894f/1024, 148f/768), new Coordinates[] { new Coordinates(0.805664f, 0.255208f), new Coordinates(0.854492f, 0.144531f), new Coordinates(0.915039f, 0.145833f), new Coordinates(0.929688f, 0.179688f), new Coordinates(0.917969f, 0.180990f), new Coordinates(0.923828f, 0.194010f), new Coordinates(0.917969f, 0.204427f), new Coordinates(0.933594f, 0.240885f), new Coordinates(0.926758f, 0.257812f), new Coordinates(0.916016f, 0.231771f), new Coordinates(0.908203f, 0.231771f), new Coordinates(0.894531f, 0.201823f), new Coordinates(0.885742f, 0.222656f), new Coordinates(0.863281f, 0.222656f), new Coordinates(0.855469f, 0.238281f), new Coordinates(0.868164f, 0.256510f) });
		Territory siria = new Territory("Siria", new Coordinates(667f/1024, 289f/768), new Coordinates[] { new Coordinates(0.700195f, 0.356771f), new Coordinates(0.691406f, 0.378906f), new Coordinates(0.699219f, 0.397135f), new Coordinates(0.621094f, 0.397135f), new Coordinates(0.621094f, 0.391927f), new Coordinates(0.612305f, 0.390625f), new Coordinates(0.604492f, 0.373698f), new Coordinates(0.613281f, 0.351562f), new Coordinates(0.630859f, 0.352865f), new Coordinates(0.630859f, 0.347656f), new Coordinates(0.644531f, 0.347656f), new Coordinates(0.648438f, 0.356771f) });
		Territory tailandia = new Territory("Tailandia", new Coordinates(891f/1024, 370f/768), new Coordinates[] { new Coordinates(0.862305f, 0.450521f), new Coordinates(0.893555f, 0.449219f), new Coordinates(0.904297f, 0.475260f), new Coordinates(0.896484f, 0.497396f), new Coordinates(0.896484f, 0.498698f), new Coordinates(0.886719f, 0.507812f), new Coordinates(0.874023f, 0.507812f), new Coordinates(0.871094f, 0.502604f), new Coordinates(0.865234f, 0.511719f), new Coordinates(0.879883f, 0.541667f), new Coordinates(0.875977f, 0.548177f), new Coordinates(0.880859f, 0.557292f), new Coordinates(0.874023f, 0.574219f), new Coordinates(0.866211f, 0.574219f), new Coordinates(0.837891f, 0.510417f) });
		Territory turquia = new Territory("Turquia", new Coordinates(713f/1024, 248f/768), new Coordinates[] { new Coordinates(0.598633f, 0.285156f), new Coordinates(0.752930f, 0.285156f), new Coordinates(0.764648f, 0.313802f), new Coordinates(0.746094f, 0.355469f), new Coordinates(0.666992f, 0.355469f), new Coordinates(0.673828f, 0.343750f), new Coordinates(0.662109f, 0.315104f), new Coordinates(0.651367f, 0.315104f), new Coordinates(0.648438f, 0.307292f), new Coordinates(0.635742f, 0.305990f), new Coordinates(0.630859f, 0.320312f), new Coordinates(0.624023f, 0.304688f), new Coordinates(0.605469f, 0.304688f) });
		
		Territory argentina = new Territory("Argentina", new Coordinates(271f/1024, 548f/768), new Coordinates[] { new Coordinates(0.267578f, 0.649740f), new Coordinates(0.233398f, 0.726562f), new Coordinates(0.228516f, 0.736979f), new Coordinates(0.271484f, 0.839844f), new Coordinates(0.288086f, 0.838542f), new Coordinates(0.275391f, 0.805990f), new Coordinates(0.303711f, 0.738281f), new Coordinates(0.295898f, 0.721354f), new Coordinates(0.324219f, 0.651042f) });
		Territory brasil = new Territory("Brasil", new Coordinates(291f/1024, 453f/768), new Coordinates[] { new Coordinates(0.247070f, 0.513021f), new Coordinates(0.252930f, 0.522135f), new Coordinates(0.278320f, 0.523438f), new Coordinates(0.286133f, 0.544271f), new Coordinates(0.297852f, 0.544271f), new Coordinates(0.308594f, 0.572917f), new Coordinates(0.327148f, 0.572917f), new Coordinates(0.333984f, 0.588542f), new Coordinates(0.335938f, 0.593750f), new Coordinates(0.317383f, 0.632812f), new Coordinates(0.325195f, 0.649740f), new Coordinates(0.267578f, 0.649740f), new Coordinates(0.228516f, 0.558594f) });
		Territory peru = new Territory("Peru", new Coordinates(236f/1024, 494f/768), new Coordinates[] { new Coordinates(0.227539f, 0.558594f), new Coordinates(0.191406f, 0.640625f), new Coordinates(0.198242f, 0.654948f), new Coordinates(0.210938f, 0.653646f), new Coordinates(0.226562f, 0.694010f), new Coordinates(0.221680f, 0.704427f), new Coordinates(0.233398f, 0.727865f), new Coordinates(0.267578f, 0.651042f) });
		Territory venezuela = new Territory("Venezuela", new Coordinates(194f/1024, 432f/768), new Coordinates[] { new Coordinates(0.157227f, 0.585938f), new Coordinates(0.188477f, 0.510417f), new Coordinates(0.247070f, 0.513021f), new Coordinates(0.227539f, 0.557292f), new Coordinates(0.191406f, 0.640625f), new Coordinates(0.184570f, 0.621094f), new Coordinates(0.170898f, 0.621094f) });
		
		Territory espanha = new Territory("Espanha", new Coordinates(447f/1024, 283f/768), new Coordinates[] { new Coordinates(0.405273f, 0.395833f), new Coordinates(0.429688f, 0.394531f), new Coordinates(0.431641f, 0.389323f), new Coordinates(0.445312f, 0.389323f), new Coordinates(0.441406f, 0.399740f), new Coordinates(0.458984f, 0.399740f), new Coordinates(0.467773f, 0.380208f), new Coordinates(0.462891f, 0.380208f), new Coordinates(0.464844f, 0.373698f), new Coordinates(0.449219f, 0.339844f), new Coordinates(0.431641f, 0.339844f) });
		Territory franca = new Territory("Franca", new Coordinates(491f/1024, 243f/768), new Coordinates[] { new Coordinates(0.465820f, 0.372396f), new Coordinates(0.471680f, 0.356771f), new Coordinates(0.482422f, 0.356771f), new Coordinates(0.489258f, 0.335938f), new Coordinates(0.496094f, 0.335938f), new Coordinates(0.525391f, 0.266927f), new Coordinates(0.513672f, 0.236979f), new Coordinates(0.502930f, 0.253906f), new Coordinates(0.508789f, 0.263021f), new Coordinates(0.503906f, 0.277344f), new Coordinates(0.487305f, 0.278646f), new Coordinates(0.476562f, 0.300781f), new Coordinates(0.437500f, 0.300781f), new Coordinates(0.440430f, 0.311198f), new Coordinates(0.448242f, 0.312500f), new Coordinates(0.455078f, 0.325521f), new Coordinates(0.450195f, 0.339844f) });
		Territory italia = new Territory("Italia", new Coordinates(535f/1024, 239f/768), new Coordinates[] { new Coordinates(0.496094f, 0.334635f), new Coordinates(0.503906f, 0.335938f), new Coordinates(0.513672f, 0.359375f), new Coordinates(0.520508f, 0.360677f), new Coordinates(0.529297f, 0.382812f), new Coordinates(0.524414f, 0.393229f), new Coordinates(0.536133f, 0.394531f), new Coordinates(0.541992f, 0.380208f), new Coordinates(0.543945f, 0.382812f), new Coordinates(0.549805f, 0.382812f), new Coordinates(0.539062f, 0.355469f), new Coordinates(0.533203f, 0.355469f), new Coordinates(0.526367f, 0.333333f), new Coordinates(0.530273f, 0.322917f), new Coordinates(0.539062f, 0.322917f), new Coordinates(0.541992f, 0.329427f), new Coordinates(0.553711f, 0.303385f), new Coordinates(0.539062f, 0.266927f), new Coordinates(0.525391f, 0.266927f) });
		Territory polonia = new Territory("Polonia", new Coordinates(572f/1024, 204f/768), new Coordinates[] { new Coordinates(0.553711f, 0.302083f), new Coordinates(0.567383f, 0.302083f), new Coordinates(0.585938f, 0.261719f), new Coordinates(0.569336f, 0.222656f), new Coordinates(0.561523f, 0.222656f), new Coordinates(0.556641f, 0.239583f), new Coordinates(0.549805f, 0.239583f), new Coordinates(0.539062f, 0.266927f) });
		Territory reinoUnido = new Territory("Reino Unido", new Coordinates(461f/1024, 175f/768), new Coordinates[] { new Coordinates(0.434570f, 0.266927f), new Coordinates(0.472656f, 0.266927f), new Coordinates(0.478516f, 0.251302f), new Coordinates(0.470703f, 0.250000f), new Coordinates(0.458008f, 0.220052f), new Coordinates(0.468750f, 0.197917f), new Coordinates(0.461914f, 0.197917f), new Coordinates(0.469727f, 0.183594f), new Coordinates(0.450195f, 0.183594f), new Coordinates(0.439453f, 0.205729f), new Coordinates(0.449219f, 0.207031f), new Coordinates(0.442383f, 0.220052f), new Coordinates(0.451172f, 0.238281f), new Coordinates(0.447266f, 0.247396f), new Coordinates(0.440430f, 0.248698f) });
		Territory romenia = new Territory("Romenia", new Coordinates(580f/1024, 267f/768), new Coordinates[] { new Coordinates(0.553711f, 0.302083f), new Coordinates(0.567383f, 0.303385f), new Coordinates(0.592773f, 0.361979f), new Coordinates(0.583984f, 0.363281f), new Coordinates(0.581055f, 0.371094f), new Coordinates(0.586914f, 0.381510f), new Coordinates(0.580078f, 0.394531f), new Coordinates(0.567383f, 0.394531f), new Coordinates(0.560547f, 0.381510f), new Coordinates(0.565430f, 0.368490f), new Coordinates(0.552734f, 0.337240f), new Coordinates(0.542969f, 0.335938f), new Coordinates(0.542969f, 0.329427f) });
		Territory suecia = new Territory("Suecia", new Coordinates(540f/1024, 133f/768), new Coordinates[] { new Coordinates(0.534180f, 0.231771f), new Coordinates(0.541016f, 0.217448f), new Coordinates(0.538086f, 0.210938f), new Coordinates(0.543945f, 0.196615f), new Coordinates(0.538086f, 0.183594f), new Coordinates(0.548828f, 0.160156f), new Coordinates(0.564453f, 0.160156f), new Coordinates(0.555664f, 0.182292f), new Coordinates(0.564453f, 0.182292f), new Coordinates(0.557617f, 0.199219f), new Coordinates(0.587891f, 0.199219f), new Coordinates(0.595703f, 0.182292f), new Coordinates(0.568359f, 0.119792f), new Coordinates(0.537109f, 0.119792f), new Coordinates(0.530273f, 0.138021f), new Coordinates(0.524414f, 0.138021f), new Coordinates(0.509766f, 0.175781f), new Coordinates(0.494141f, 0.177083f), new Coordinates(0.479492f, 0.210938f), new Coordinates(0.488281f, 0.231771f), new Coordinates(0.501953f, 0.231771f), new Coordinates(0.508789f, 0.216146f), new Coordinates(0.517578f, 0.216146f), new Coordinates(0.522461f, 0.231771f) });
		Territory ucrania = new Territory("Ucrania", new Coordinates(604f/1024, 238f/768), new Coordinates[] { new Coordinates(0.568359f, 0.303385f), new Coordinates(0.592773f, 0.361979f), new Coordinates(0.604492f, 0.338542f), new Coordinates(0.596680f, 0.324219f), new Coordinates(0.604492f, 0.304688f), new Coordinates(0.585938f, 0.263021f) });
		
		
		Territory australia = new Territory("Australia", new Coordinates(869f/1024, 608f/768), new Coordinates[] { new Coordinates(0.796875f, 0.828125f), new Coordinates(0.803711f, 0.847656f), new Coordinates(0.817383f, 0.847656f), new Coordinates(0.826172f, 0.869792f), new Coordinates(0.856445f, 0.869792f), new Coordinates(0.869141f, 0.839844f), new Coordinates(0.879883f, 0.839844f), new Coordinates(0.895508f, 0.803385f), new Coordinates(0.889648f, 0.789062f), new Coordinates(0.897461f, 0.772135f), new Coordinates(0.865234f, 0.696615f), new Coordinates(0.853516f, 0.695312f) });
		Territory indonesia = new Territory("Indonesia", new Coordinates(892f/1024, 507f/768), new Coordinates[] { new Coordinates(0.821289f, 0.619792f), new Coordinates(0.831055f, 0.619792f), new Coordinates(0.839844f, 0.644531f), new Coordinates(0.859375f, 0.644531f), new Coordinates(0.866211f, 0.626302f), new Coordinates(0.881836f, 0.626302f), new Coordinates(0.885742f, 0.641927f), new Coordinates(0.903320f, 0.641927f), new Coordinates(0.913086f, 0.666667f), new Coordinates(0.920898f, 0.667969f), new Coordinates(0.930664f, 0.691406f), new Coordinates(0.905273f, 0.691406f), new Coordinates(0.898438f, 0.673177f), new Coordinates(0.884766f, 0.673177f), new Coordinates(0.882812f, 0.682292f), new Coordinates(0.868164f, 0.680990f), new Coordinates(0.864258f, 0.667969f), new Coordinates(0.828125f, 0.666667f), new Coordinates(0.815430f, 0.631510f) });
		Territory novaZelandia = new Territory("Nova Zelandia", new Coordinates(926f/1024, 645f/768), new Coordinates[] { new Coordinates(0.906250f, 0.781250f), new Coordinates(0.915039f, 0.781250f), new Coordinates(0.919922f, 0.796875f), new Coordinates(0.916016f, 0.800781f), new Coordinates(0.921875f, 0.800781f), new Coordinates(0.927734f, 0.817708f), new Coordinates(0.909180f, 0.859375f), new Coordinates(0.904297f, 0.859375f), new Coordinates(0.895508f, 0.881510f), new Coordinates(0.877930f, 0.880208f), new Coordinates(0.894531f, 0.837240f), new Coordinates(0.901367f, 0.835938f), new Coordinates(0.910156f, 0.815104f), new Coordinates(0.906250f, 0.804688f), new Coordinates(0.911133f, 0.791667f) });
		Territory perth = new Territory("Perth", new Coordinates(803f/1024, 597f/768), new Coordinates[] { new Coordinates(0.738281f, 0.826823f), new Coordinates(0.748047f, 0.847656f), new Coordinates(0.761719f, 0.847656f), new Coordinates(0.768555f, 0.829427f), new Coordinates(0.795898f, 0.828125f), new Coordinates(0.840820f, 0.725260f), new Coordinates(0.833984f, 0.709635f), new Coordinates(0.841797f, 0.699219f), new Coordinates(0.835938f, 0.691406f), new Coordinates(0.818359f, 0.691406f), new Coordinates(0.802734f, 0.729167f), new Coordinates(0.779297f, 0.730469f), new Coordinates(0.770508f, 0.753906f), new Coordinates(0.761719f, 0.753906f), new Coordinates(0.755859f, 0.770833f), new Coordinates(0.763672f, 0.785156f), new Coordinates(0.763672f, 0.790365f), new Coordinates(0.751953f, 0.813802f), new Coordinates(0.745117f, 0.813802f) });
		
		
		africaDoSul.addNeighbour(angola);
		africaDoSul.addNeighbour(somalia);
		
		angola.addNeighbour(somalia);
		angola.addNeighbour(nigeria);
		
		argelia.addNeighbour(italia);
		argelia.addNeighbour(espanha);
		argelia.addNeighbour(nigeria);
		argelia.addNeighbour(egito);
		
		egito.addNeighbour(nigeria);
		egito.addNeighbour(somalia);
		egito.addNeighbour(romenia);
		egito.addNeighbour(jordania);
		
		nigeria.addNeighbour(somalia);
		nigeria.addNeighbour(brasil);

		somalia.addNeighbour(arabiaSaudita);
		argelia.addNeighbour(italia);
		argelia.addNeighbour(espanha);
		
		map.continents[0] = new Continent("Africa", new Territory[] {
				africaDoSul,angola, argelia, egito, nigeria, somalia
		}, 3, new Color(101, 45, 144));
		
		
		alasca.addNeighbour(calgary);
		alasca.addNeighbour(vancouver);
		alasca.addNeighbour(siberia);
		
		calgary.addNeighbour(vancouver);
		calgary.addNeighbour(groelandia);
		
		california.addNeighbour(vancouver);
		california.addNeighbour(texas);
		california.addNeighbour(mexico);
		
		groelandia.addNeighbour(quebec);
		groelandia.addNeighbour(reinoUnido);
		
		mexico.addNeighbour(texas);
		mexico.addNeighbour(venezuela);
		
		novaYork.addNeighbour(texas);
		novaYork.addNeighbour(quebec);

		quebec.addNeighbour(texas);
		quebec.addNeighbour(vancouver);
		
		texas.addNeighbour(vancouver);

		
		
		map.continents[1] = new Continent("America do Norte", new Territory[] {
				alasca, calgary, california, groelandia, mexico, novaYork, quebec, texas, vancouver
		}, 5, new Color(238, 64, 54));
		
		
		arabiaSaudita.addNeighbour(iraque);
		arabiaSaudita.addNeighbour(jordania);
		
		bangladesh.addNeighbour(tailandia);
		bangladesh.addNeighbour(india);
		bangladesh.addNeighbour(coreiaDoSul);
		bangladesh.addNeighbour(indonesia);
		
		cazaquistao.addNeighbour(japao);
		cazaquistao.addNeighbour(siberia);
		cazaquistao.addNeighbour(russia);
		cazaquistao.addNeighbour(mongolia);
		cazaquistao.addNeighbour(china);
		cazaquistao.addNeighbour(turquia);
		cazaquistao.addNeighbour(letonia);
		
		china.addNeighbour(mongolia);
		china.addNeighbour(coreiaDoNorte);
		china.addNeighbour(coreiaDoSul);
		china.addNeighbour(india);
		china.addNeighbour(paquistao);
		china.addNeighbour(turquia);
		
		coreiaDoNorte.addNeighbour(japao);
		coreiaDoNorte.addNeighbour(coreiaDoSul);
		
		coreiaDoSul.addNeighbour(tailandia);
		coreiaDoSul.addNeighbour(india);
		
		estonia.addNeighbour(russia);
		estonia.addNeighbour(letonia);
		estonia.addNeighbour(suecia);
		
		india.addNeighbour(paquistao);
		india.addNeighbour(indonesia);
		
		ira.addNeighbour(paquistao);
		ira.addNeighbour(siria);
		ira.addNeighbour(iraque);
		
		iraque.addNeighbour(siria);
		iraque.addNeighbour(jordania);
		
		japao.addNeighbour(mongolia);
		
		jordania.addNeighbour(siria);
		
		
		letonia.addNeighbour(russia);
		letonia.addNeighbour(turquia);
		letonia.addNeighbour(ucrania);
		letonia.addNeighbour(polonia);
		letonia.addNeighbour(suecia);
		
		paquistao.addNeighbour(turquia);
		paquistao.addNeighbour(siria);
		
		russia.addNeighbour(siberia);
		
		siria.addNeighbour(turquia);
		
		turquia.addNeighbour(ucrania);
		
		map.continents[2] = new Continent("Asia", new Territory[] {
				arabiaSaudita, bangladesh, cazaquistao, china, coreiaDoNorte, coreiaDoSul, 
				estonia, india, ira, iraque, japao, jordania, letonia, mongolia, paquistao, 
				russia, siberia, siria, tailandia, turquia  
		}, 7, new Color(246, 146, 30));
		
		
		
		argentina.addNeighbour(peru);
		argentina.addNeighbour(brasil);
		
		brasil.addNeighbour(peru);
		brasil.addNeighbour(venezuela);
		
		peru.addNeighbour(venezuela);
		
		map.continents[3] = new Continent("America do Sul", new Territory[] {
				argentina, brasil, peru, venezuela
		}, 2, new Color(0, 104, 58));
		
		
		espanha.addNeighbour(franca);
		
		franca.addNeighbour(italia);
		franca.addNeighbour(suecia);
		franca.addNeighbour(reinoUnido);
		
		italia.addNeighbour(romenia);
		italia.addNeighbour(polonia);
		italia.addNeighbour(suecia);
		
		polonia.addNeighbour(ucrania);
		polonia.addNeighbour(romenia);
		
		romenia.addNeighbour(ucrania);
		
		map.continents[4] = new Continent("Europa", new Territory[] {
				espanha, franca, italia, polonia, reinoUnido, romenia, suecia, ucrania
		}, 5, new Color(43, 56, 143));
		
		australia.addNeighbour(perth);
		australia.addNeighbour(indonesia);
		australia.addNeighbour(novaZelandia);
		

		map.continents[5] = new Continent("Oceania", new Territory[] {
				indonesia, novaZelandia,
				australia, perth
		}, 2, new Color(38, 169, 224));
				
		return map;
		
	}
	
	public Territory findTerritory(String name) {
	    for (Continent continent : continents) {
	        Territory territory = continent.findTerritory(name);
	        if (territory != null) {
	            return territory;
	        }
	    }
	    return null;
	}
	
	public int getNumberTerritories() {
	    int count = 0;
	    for (int i = 0; i < continents.length; ++i) {
			count += continents[i].getNumberTerritories();
		}
	    return count;
	}
	
	
	public Continent findContinent(String name) {
	    for (Continent continent : continents) {
	        if (continent.getName().equals(name)) {
	            return continent;
	        }
	    }
	    return null;
	}
}
	