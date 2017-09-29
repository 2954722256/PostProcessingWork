package jet.opengl.demos.nvidia.face.libs;

/**
 * Runtime config struct for SSS.<p></p>
 * Created by mazhen'gui on 2017/9/4.
 */

public class GFSDK_FaceWorks_SSSConfig {
    /** Diffusion radius, in world units (= 2.7mm for human skin) */
    public float		m_diffusionRadius;
    /** Diffusion radius used to build the LUTs */
    public float		m_diffusionRadiusLUT;
    /** Min radius of curvature used to build the LUT */
    public float		m_curvatureRadiusMinLUT;
    /** Max radius of curvature used to build the LUT */
    public float		m_curvatureRadiusMaxLUT;
    /** Min world-space penumbra width used to build the LUT */
    public float		m_shadowWidthMinLUT;
    /** Max world-space penumbra width used to build the LUT */
    public float		m_shadowWidthMaxLUT;
    /** World-space width of shadow filter */
    public float		m_shadowFilterWidth;	
    /** Pixel size of normal map */
    public int			m_normalMapSize;
    /** Average UV scale of the mesh, i.e. world-space size of UV unit square */
    public float		m_averageUVScale;		
}