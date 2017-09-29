/*
Open Asset Import Library (assimp)
----------------------------------------------------------------------

Copyright (c) 2006-2012, assimp team
All rights reserved.

Redistribution and use of this software in source and binary forms, 
with or without modification, are permitted provided that the 
following conditions are met:

* Redistributions of source code must retain the above
  copyright notice, this list of conditions and the
  following disclaimer.

* Redistributions in binary form must reproduce the above
  copyright notice, this list of conditions and the
  following disclaimer in the documentation and/or other
  materials provided with the distribution.

* Neither the name of the assimp team, nor the names of its
  contributors may be used to endorse or promote products
  derived from this software without specific prior
  written permission of the assimp team.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT 
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

----------------------------------------------------------------------
*/
package assimp.importer.d3ds;

public interface D3DSHelper {

	//! Used for shading field in material3ds structure
	//! From AutoDesk 3ds SDK
//	typedef enum
//	{
	/** translated to gouraud shading with wireframe active */
	public static final int Wire = 0x0;

	/** if this material is set, no vertex normals will
	 * be calculated for the model. Face normals + gouraud */
	public static final int Flat = 0x1;

	/** standard gouraud shading */
	public static final int Gouraud = 0x2;

	/** phong shading */
	public static final int Phong = 0x3;

	/** cooktorrance or anistropic phong shading ...
	 * the exact meaning is unknown, if you know it
	 * feel free to tell me ;-) */
	public static final int Metal = 0x4;

	/** required by the ASE loader */
	public static final int Blinn = 0x5;
//	} shadetype3ds;
	
	// Flags for animated keys
//	enum
//	{
	static final int
		KEY_USE_TENS         = 0x1,
		KEY_USE_CONT         = 0x2,
		KEY_USE_BIAS         = 0x4,
		KEY_USE_EASE_TO      = 0x8,
		KEY_USE_EASE_FROM    = 0x10;
//	} ;
	
//	enum 
//	{
	static final int

		// ********************************************************************
		// Basic chunks which can be found everywhere in the file
		CHUNK_VERSION	= 0x0002,
		CHUNK_RGBF      = 0x0010,		// float4 R; float4 G; float4 B
		CHUNK_RGBB      = 0x0011,		// int1 R; int1 G; int B

		// Linear color values (gamma = 2.2?)
		CHUNK_LINRGBF      = 0x0013,	// float4 R; float4 G; float4 B
		CHUNK_LINRGBB      = 0x0012,	// int1 R; int1 G; int B

		CHUNK_PERCENTW	= 0x0030,		// int2   percentage
		CHUNK_PERCENTF	= 0x0031,		// float4  percentage
		// ********************************************************************

		// Prj master chunk
		CHUNK_PRJ       = 0xC23D,

		// MDLI master chunk
		CHUNK_MLI       = 0x3DAA,

		// Primary main chunk of the .3ds file
		CHUNK_MAIN      = 0x4D4D,

		// Mesh main chunk
		CHUNK_OBJMESH   = 0x3D3D,

		// Specifies the background color of the .3ds file
		// This is passed through the material system for
		// viewing purposes.
		CHUNK_BKGCOLOR  = 0x1200,

		// Specifies the ambient base color of the scene.
		// This is added to all materials in the file
		CHUNK_AMBCOLOR  = 0x2100,

		// Specifies the background image for the whole scene
		// This value is passed through the material system
		// to the viewer 
		CHUNK_BIT_MAP   = 0x1100,
		CHUNK_BIT_MAP_EXISTS  = 0x1101,

		// ********************************************************************
		// Viewport related stuff. Ignored
		CHUNK_DEFAULT_VIEW = 0x3000,
		CHUNK_VIEW_TOP = 0x3010,
		CHUNK_VIEW_BOTTOM = 0x3020,
		CHUNK_VIEW_LEFT = 0x3030,
		CHUNK_VIEW_RIGHT = 0x3040,
		CHUNK_VIEW_FRONT = 0x3050,
		CHUNK_VIEW_BACK = 0x3060,
		CHUNK_VIEW_USER = 0x3070,
		CHUNK_VIEW_CAMERA = 0x3080,
		// ********************************************************************

		// Mesh chunks
		CHUNK_OBJBLOCK  = 0x4000,
		CHUNK_TRIMESH   = 0x4100,
		CHUNK_VERTLIST  = 0x4110,
		CHUNK_VERTFLAGS = 0x4111,
		CHUNK_FACELIST  = 0x4120,
		CHUNK_FACEMAT   = 0x4130,
		CHUNK_MAPLIST   = 0x4140,
		CHUNK_SMOOLIST  = 0x4150,
		CHUNK_TRMATRIX  = 0x4160,
		CHUNK_MESHCOLOR = 0x4165,
		CHUNK_TXTINFO   = 0x4170,
		CHUNK_LIGHT     = 0x4600,
		CHUNK_CAMERA    = 0x4700,
		CHUNK_HIERARCHY = 0x4F00,

		// Specifies the global scaling factor. This is applied
		// to the root node's transformation matrix
		CHUNK_MASTER_SCALE    = 0x0100,

		// ********************************************************************
		// Material chunks
		CHUNK_MAT_MATERIAL  = 0xAFFF,

			// asciiz containing the name of the material
			CHUNK_MAT_MATNAME   = 0xA000, 
			CHUNK_MAT_AMBIENT   = 0xA010, // followed by color chunk
			CHUNK_MAT_DIFFUSE   = 0xA020, // followed by color chunk
			CHUNK_MAT_SPECULAR  = 0xA030, // followed by color chunk

			// Specifies the shininess of the material
			// followed by percentage chunk
			CHUNK_MAT_SHININESS  = 0xA040, 
			CHUNK_MAT_SHININESS_PERCENT  = 0xA041 ,

			// Specifies the shading mode to be used
			// followed by a short
			CHUNK_MAT_SHADING  = 0xA100, 

			// NOTE: Emissive color (self illumination) seems not
			// to be a color but a single value, type is unknown.
			// Make the parser accept both of them.
			// followed by percentage chunk (?)
			CHUNK_MAT_SELF_ILLUM = 0xA080,  

			// Always followed by percentage chunk	(?)
			CHUNK_MAT_SELF_ILPCT = 0xA084,  

			// Always followed by percentage chunk
			CHUNK_MAT_TRANSPARENCY = 0xA050, 

			// Diffuse texture channel 0 
			CHUNK_MAT_TEXTURE   = 0xA200,

			// Contains opacity information for each texel
			CHUNK_MAT_OPACMAP = 0xA210,

			// Contains a reflection map to be used to reflect
			// the environment. This is partially supported.
			CHUNK_MAT_REFLMAP = 0xA220,

			// Self Illumination map (emissive colors)
			CHUNK_MAT_SELFIMAP = 0xA33d,	

			// Bumpmap. Not specified whether it is a heightmap
			// or a normal map. Assme it is a heightmap since
			// artist normally prefer this format.
			CHUNK_MAT_BUMPMAP = 0xA230,

			// Specular map. Seems to influence the specular color
			CHUNK_MAT_SPECMAP = 0xA204,

			// Holds shininess data. 
			CHUNK_MAT_MAT_SHINMAP = 0xA33C,

			// Scaling in U/V direction.
			// (need to gen separate UV coordinate set 
			// and do this by hand)
			CHUNK_MAT_MAP_USCALE 	  = 0xA354,
			CHUNK_MAT_MAP_VSCALE 	  = 0xA356,

			// Translation in U/V direction.
			// (need to gen separate UV coordinate set 
			// and do this by hand)
			CHUNK_MAT_MAP_UOFFSET 	  = 0xA358,
			CHUNK_MAT_MAP_VOFFSET 	  = 0xA35a,

			// UV-coordinates rotation around the z-axis
			// Assumed to be in radians.
			CHUNK_MAT_MAP_ANG = 0xA35C,

			// Tiling flags for 3DS files
			CHUNK_MAT_MAP_TILING = 0xa351,

			// Specifies the file name of a texture
			CHUNK_MAPFILE   = 0xA300,

			// Specifies whether a materail requires two-sided rendering
			CHUNK_MAT_TWO_SIDE = 0xA081,  
		// ********************************************************************

		// Main keyframer chunk. Contains translation/rotation/scaling data
		CHUNK_KEYFRAMER		= 0xB000,

		// Supported sub chunks
		CHUNK_TRACKINFO		= 0xB002,
		CHUNK_TRACKOBJNAME  = 0xB010,
		CHUNK_TRACKDUMMYOBJNAME  = 0xB011,
		CHUNK_TRACKPIVOT    = 0xB013,
		CHUNK_TRACKPOS      = 0xB020,
		CHUNK_TRACKROTATE   = 0xB021,
		CHUNK_TRACKSCALE    = 0xB022,

		// ********************************************************************
		// Keyframes for various other stuff in the file
		// Partially ignored
		CHUNK_AMBIENTKEY    = 0xB001,
		CHUNK_TRACKMORPH    = 0xB026,
		CHUNK_TRACKHIDE     = 0xB029,
		CHUNK_OBJNUMBER     = 0xB030,
		CHUNK_TRACKCAMERA	= 0xB003,
		CHUNK_TRACKFOV		= 0xB023,
		CHUNK_TRACKROLL		= 0xB024,
		CHUNK_TRACKCAMTGT	= 0xB004,
		CHUNK_TRACKLIGHT	= 0xB005,
		CHUNK_TRACKLIGTGT	= 0xB006,
		CHUNK_TRACKSPOTL	= 0xB007,
		CHUNK_FRAMES		= 0xB008,
		// ********************************************************************

		// light sub-chunks
		CHUNK_DL_OFF                 = 0x4620,
		CHUNK_DL_OUTER_RANGE         = 0x465A,
		CHUNK_DL_INNER_RANGE         = 0x4659,
		CHUNK_DL_MULTIPLIER          = 0x465B,
		CHUNK_DL_EXCLUDE             = 0x4654,
		CHUNK_DL_ATTENUATE           = 0x4625,
		CHUNK_DL_SPOTLIGHT           = 0x4610,

		// camera sub-chunks
		CHUNK_CAM_RANGES             = 0x4720;
//	};
	
	
}