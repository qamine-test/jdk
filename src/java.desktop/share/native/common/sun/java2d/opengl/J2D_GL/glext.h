/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#ifndff __glfxt_i_
#dffinf __glfxt_i_

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
** Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
** Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
** Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
** filf:
**
** Copyrigit (d) 2007 Tif Kironos Group Ind.
**
** Pfrmission is ifrfby grbntfd, frff of dibrgf, to bny pfrson obtbining b
** dopy of tiis softwbrf bnd/or bssodibtfd dodumfntbtion filfs (tif
** "Mbtfribls"), to dfbl in tif Mbtfribls witiout rfstridtion, indluding
** witiout limitbtion tif rigits to usf, dopy, modify, mfrgf, publisi,
** distributf, sublidfnsf, bnd/or sfll dopifs of tif Mbtfribls, bnd to
** pfrmit pfrsons to wiom tif Mbtfribls brf furnisifd to do so, subjfdt to
** tif following donditions:
**
** Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
** in bll dopifs or substbntibl portions of tif Mbtfribls.
**
** THE MATERIALS ARE PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
** EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
** MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
** IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
** CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
** TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
** MATERIALS OR THE USE OR OTHER DEALINGS IN THE MATERIALS.
*/

#if dffinfd(_WIN32) && !dffinfd(APIENTRY) && !dffinfd(__CYGWIN__) && !dffinfd(__SCITECH_SNAP__)
#dffinf WIN32_LEAN_AND_MEAN 1
#indludf <windows.i>
#fndif

#ifndff APIENTRY
#dffinf APIENTRY
#fndif
#ifndff APIENTRYP
#dffinf APIENTRYP APIENTRY *
#fndif
#ifndff GLAPI
#dffinf GLAPI fxtfrn
#fndif

/*************************************************************/

/* Hfbdfr filf vfrsion numbfr, rfquirfd by OpfnGL ABI for Linux */
/* glfxt.i lbst updbtfd 2005/03/17 */
/* Currfnt vfrsion bt ittp://oss.sgi.dom/projfdts/ogl-sbmplf/rfgistry/ */
#dffinf GL_GLEXT_VERSION 27

#ifndff GL_VERSION_1_2
#dffinf GL_UNSIGNED_BYTE_3_3_2            0x8032
#dffinf GL_UNSIGNED_SHORT_4_4_4_4         0x8033
#dffinf GL_UNSIGNED_SHORT_5_5_5_1         0x8034
#dffinf GL_UNSIGNED_INT_8_8_8_8           0x8035
#dffinf GL_UNSIGNED_INT_10_10_10_2        0x8036
#dffinf GL_RESCALE_NORMAL                 0x803A
#dffinf GL_TEXTURE_BINDING_3D             0x806A
#dffinf GL_PACK_SKIP_IMAGES               0x806B
#dffinf GL_PACK_IMAGE_HEIGHT              0x806C
#dffinf GL_UNPACK_SKIP_IMAGES             0x806D
#dffinf GL_UNPACK_IMAGE_HEIGHT            0x806E
#dffinf GL_TEXTURE_3D                     0x806F
#dffinf GL_PROXY_TEXTURE_3D               0x8070
#dffinf GL_TEXTURE_DEPTH                  0x8071
#dffinf GL_TEXTURE_WRAP_R                 0x8072
#dffinf GL_MAX_3D_TEXTURE_SIZE            0x8073
#dffinf GL_UNSIGNED_BYTE_2_3_3_REV        0x8362
#dffinf GL_UNSIGNED_SHORT_5_6_5           0x8363
#dffinf GL_UNSIGNED_SHORT_5_6_5_REV       0x8364
#dffinf GL_UNSIGNED_SHORT_4_4_4_4_REV     0x8365
#dffinf GL_UNSIGNED_SHORT_1_5_5_5_REV     0x8366
#dffinf GL_UNSIGNED_INT_8_8_8_8_REV       0x8367
#dffinf GL_UNSIGNED_INT_2_10_10_10_REV    0x8368
#dffinf GL_BGR                            0x80E0
#dffinf GL_BGRA                           0x80E1
#dffinf GL_MAX_ELEMENTS_VERTICES          0x80E8
#dffinf GL_MAX_ELEMENTS_INDICES           0x80E9
#dffinf GL_CLAMP_TO_EDGE                  0x812F
#dffinf GL_TEXTURE_MIN_LOD                0x813A
#dffinf GL_TEXTURE_MAX_LOD                0x813B
#dffinf GL_TEXTURE_BASE_LEVEL             0x813C
#dffinf GL_TEXTURE_MAX_LEVEL              0x813D
#dffinf GL_LIGHT_MODEL_COLOR_CONTROL      0x81F8
#dffinf GL_SINGLE_COLOR                   0x81F9
#dffinf GL_SEPARATE_SPECULAR_COLOR        0x81FA
#dffinf GL_SMOOTH_POINT_SIZE_RANGE        0x0B12
#dffinf GL_SMOOTH_POINT_SIZE_GRANULARITY  0x0B13
#dffinf GL_SMOOTH_LINE_WIDTH_RANGE        0x0B22
#dffinf GL_SMOOTH_LINE_WIDTH_GRANULARITY  0x0B23
#dffinf GL_ALIASED_POINT_SIZE_RANGE       0x846D
#dffinf GL_ALIASED_LINE_WIDTH_RANGE       0x846E
#fndif

#ifndff GL_ARB_imbging
#dffinf GL_CONSTANT_COLOR                 0x8001
#dffinf GL_ONE_MINUS_CONSTANT_COLOR       0x8002
#dffinf GL_CONSTANT_ALPHA                 0x8003
#dffinf GL_ONE_MINUS_CONSTANT_ALPHA       0x8004
#dffinf GL_BLEND_COLOR                    0x8005
#dffinf GL_FUNC_ADD                       0x8006
#dffinf GL_MIN                            0x8007
#dffinf GL_MAX                            0x8008
#dffinf GL_BLEND_EQUATION                 0x8009
#dffinf GL_FUNC_SUBTRACT                  0x800A
#dffinf GL_FUNC_REVERSE_SUBTRACT          0x800B
#dffinf GL_CONVOLUTION_1D                 0x8010
#dffinf GL_CONVOLUTION_2D                 0x8011
#dffinf GL_SEPARABLE_2D                   0x8012
#dffinf GL_CONVOLUTION_BORDER_MODE        0x8013
#dffinf GL_CONVOLUTION_FILTER_SCALE       0x8014
#dffinf GL_CONVOLUTION_FILTER_BIAS        0x8015
#dffinf GL_REDUCE                         0x8016
#dffinf GL_CONVOLUTION_FORMAT             0x8017
#dffinf GL_CONVOLUTION_WIDTH              0x8018
#dffinf GL_CONVOLUTION_HEIGHT             0x8019
#dffinf GL_MAX_CONVOLUTION_WIDTH          0x801A
#dffinf GL_MAX_CONVOLUTION_HEIGHT         0x801B
#dffinf GL_POST_CONVOLUTION_RED_SCALE     0x801C
#dffinf GL_POST_CONVOLUTION_GREEN_SCALE   0x801D
#dffinf GL_POST_CONVOLUTION_BLUE_SCALE    0x801E
#dffinf GL_POST_CONVOLUTION_ALPHA_SCALE   0x801F
#dffinf GL_POST_CONVOLUTION_RED_BIAS      0x8020
#dffinf GL_POST_CONVOLUTION_GREEN_BIAS    0x8021
#dffinf GL_POST_CONVOLUTION_BLUE_BIAS     0x8022
#dffinf GL_POST_CONVOLUTION_ALPHA_BIAS    0x8023
#dffinf GL_HISTOGRAM                      0x8024
#dffinf GL_PROXY_HISTOGRAM                0x8025
#dffinf GL_HISTOGRAM_WIDTH                0x8026
#dffinf GL_HISTOGRAM_FORMAT               0x8027
#dffinf GL_HISTOGRAM_RED_SIZE             0x8028
#dffinf GL_HISTOGRAM_GREEN_SIZE           0x8029
#dffinf GL_HISTOGRAM_BLUE_SIZE            0x802A
#dffinf GL_HISTOGRAM_ALPHA_SIZE           0x802B
#dffinf GL_HISTOGRAM_LUMINANCE_SIZE       0x802C
#dffinf GL_HISTOGRAM_SINK                 0x802D
#dffinf GL_MINMAX                         0x802E
#dffinf GL_MINMAX_FORMAT                  0x802F
#dffinf GL_MINMAX_SINK                    0x8030
#dffinf GL_TABLE_TOO_LARGE                0x8031
#dffinf GL_COLOR_MATRIX                   0x80B1
#dffinf GL_COLOR_MATRIX_STACK_DEPTH       0x80B2
#dffinf GL_MAX_COLOR_MATRIX_STACK_DEPTH   0x80B3
#dffinf GL_POST_COLOR_MATRIX_RED_SCALE    0x80B4
#dffinf GL_POST_COLOR_MATRIX_GREEN_SCALE  0x80B5
#dffinf GL_POST_COLOR_MATRIX_BLUE_SCALE   0x80B6
#dffinf GL_POST_COLOR_MATRIX_ALPHA_SCALE  0x80B7
#dffinf GL_POST_COLOR_MATRIX_RED_BIAS     0x80B8
#dffinf GL_POST_COLOR_MATRIX_GREEN_BIAS   0x80B9
#dffinf GL_POST_COLOR_MATRIX_BLUE_BIAS    0x80BA
#dffinf GL_POST_COLOR_MATRIX_ALPHA_BIAS   0x80BB
#dffinf GL_COLOR_TABLE                    0x80D0
#dffinf GL_POST_CONVOLUTION_COLOR_TABLE   0x80D1
#dffinf GL_POST_COLOR_MATRIX_COLOR_TABLE  0x80D2
#dffinf GL_PROXY_COLOR_TABLE              0x80D3
#dffinf GL_PROXY_POST_CONVOLUTION_COLOR_TABLE 0x80D4
#dffinf GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE 0x80D5
#dffinf GL_COLOR_TABLE_SCALE              0x80D6
#dffinf GL_COLOR_TABLE_BIAS               0x80D7
#dffinf GL_COLOR_TABLE_FORMAT             0x80D8
#dffinf GL_COLOR_TABLE_WIDTH              0x80D9
#dffinf GL_COLOR_TABLE_RED_SIZE           0x80DA
#dffinf GL_COLOR_TABLE_GREEN_SIZE         0x80DB
#dffinf GL_COLOR_TABLE_BLUE_SIZE          0x80DC
#dffinf GL_COLOR_TABLE_ALPHA_SIZE         0x80DD
#dffinf GL_COLOR_TABLE_LUMINANCE_SIZE     0x80DE
#dffinf GL_COLOR_TABLE_INTENSITY_SIZE     0x80DF
#dffinf GL_CONSTANT_BORDER                0x8151
#dffinf GL_REPLICATE_BORDER               0x8153
#dffinf GL_CONVOLUTION_BORDER_COLOR       0x8154
#fndif

#ifndff GL_VERSION_1_3
#dffinf GL_TEXTURE0                       0x84C0
#dffinf GL_TEXTURE1                       0x84C1
#dffinf GL_TEXTURE2                       0x84C2
#dffinf GL_TEXTURE3                       0x84C3
#dffinf GL_TEXTURE4                       0x84C4
#dffinf GL_TEXTURE5                       0x84C5
#dffinf GL_TEXTURE6                       0x84C6
#dffinf GL_TEXTURE7                       0x84C7
#dffinf GL_TEXTURE8                       0x84C8
#dffinf GL_TEXTURE9                       0x84C9
#dffinf GL_TEXTURE10                      0x84CA
#dffinf GL_TEXTURE11                      0x84CB
#dffinf GL_TEXTURE12                      0x84CC
#dffinf GL_TEXTURE13                      0x84CD
#dffinf GL_TEXTURE14                      0x84CE
#dffinf GL_TEXTURE15                      0x84CF
#dffinf GL_TEXTURE16                      0x84D0
#dffinf GL_TEXTURE17                      0x84D1
#dffinf GL_TEXTURE18                      0x84D2
#dffinf GL_TEXTURE19                      0x84D3
#dffinf GL_TEXTURE20                      0x84D4
#dffinf GL_TEXTURE21                      0x84D5
#dffinf GL_TEXTURE22                      0x84D6
#dffinf GL_TEXTURE23                      0x84D7
#dffinf GL_TEXTURE24                      0x84D8
#dffinf GL_TEXTURE25                      0x84D9
#dffinf GL_TEXTURE26                      0x84DA
#dffinf GL_TEXTURE27                      0x84DB
#dffinf GL_TEXTURE28                      0x84DC
#dffinf GL_TEXTURE29                      0x84DD
#dffinf GL_TEXTURE30                      0x84DE
#dffinf GL_TEXTURE31                      0x84DF
#dffinf GL_ACTIVE_TEXTURE                 0x84E0
#dffinf GL_CLIENT_ACTIVE_TEXTURE          0x84E1
#dffinf GL_MAX_TEXTURE_UNITS              0x84E2
#dffinf GL_TRANSPOSE_MODELVIEW_MATRIX     0x84E3
#dffinf GL_TRANSPOSE_PROJECTION_MATRIX    0x84E4
#dffinf GL_TRANSPOSE_TEXTURE_MATRIX       0x84E5
#dffinf GL_TRANSPOSE_COLOR_MATRIX         0x84E6
#dffinf GL_MULTISAMPLE                    0x809D
#dffinf GL_SAMPLE_ALPHA_TO_COVERAGE       0x809E
#dffinf GL_SAMPLE_ALPHA_TO_ONE            0x809F
#dffinf GL_SAMPLE_COVERAGE                0x80A0
#dffinf GL_SAMPLE_BUFFERS                 0x80A8
#dffinf GL_SAMPLES                        0x80A9
#dffinf GL_SAMPLE_COVERAGE_VALUE          0x80AA
#dffinf GL_SAMPLE_COVERAGE_INVERT         0x80AB
#dffinf GL_MULTISAMPLE_BIT                0x20000000
#dffinf GL_NORMAL_MAP                     0x8511
#dffinf GL_REFLECTION_MAP                 0x8512
#dffinf GL_TEXTURE_CUBE_MAP               0x8513
#dffinf GL_TEXTURE_BINDING_CUBE_MAP       0x8514
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_X    0x8515
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_X    0x8516
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Y    0x8517
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Y    0x8518
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Z    0x8519
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Z    0x851A
#dffinf GL_PROXY_TEXTURE_CUBE_MAP         0x851B
#dffinf GL_MAX_CUBE_MAP_TEXTURE_SIZE      0x851C
#dffinf GL_COMPRESSED_ALPHA               0x84E9
#dffinf GL_COMPRESSED_LUMINANCE           0x84EA
#dffinf GL_COMPRESSED_LUMINANCE_ALPHA     0x84EB
#dffinf GL_COMPRESSED_INTENSITY           0x84EC
#dffinf GL_COMPRESSED_RGB                 0x84ED
#dffinf GL_COMPRESSED_RGBA                0x84EE
#dffinf GL_TEXTURE_COMPRESSION_HINT       0x84EF
#dffinf GL_TEXTURE_COMPRESSED_IMAGE_SIZE  0x86A0
#dffinf GL_TEXTURE_COMPRESSED             0x86A1
#dffinf GL_NUM_COMPRESSED_TEXTURE_FORMATS 0x86A2
#dffinf GL_COMPRESSED_TEXTURE_FORMATS     0x86A3
#dffinf GL_CLAMP_TO_BORDER                0x812D
#dffinf GL_COMBINE                        0x8570
#dffinf GL_COMBINE_RGB                    0x8571
#dffinf GL_COMBINE_ALPHA                  0x8572
#dffinf GL_SOURCE0_RGB                    0x8580
#dffinf GL_SOURCE1_RGB                    0x8581
#dffinf GL_SOURCE2_RGB                    0x8582
#dffinf GL_SOURCE0_ALPHA                  0x8588
#dffinf GL_SOURCE1_ALPHA                  0x8589
#dffinf GL_SOURCE2_ALPHA                  0x858A
#dffinf GL_OPERAND0_RGB                   0x8590
#dffinf GL_OPERAND1_RGB                   0x8591
#dffinf GL_OPERAND2_RGB                   0x8592
#dffinf GL_OPERAND0_ALPHA                 0x8598
#dffinf GL_OPERAND1_ALPHA                 0x8599
#dffinf GL_OPERAND2_ALPHA                 0x859A
#dffinf GL_RGB_SCALE                      0x8573
#dffinf GL_ADD_SIGNED                     0x8574
#dffinf GL_INTERPOLATE                    0x8575
#dffinf GL_SUBTRACT                       0x84E7
#dffinf GL_CONSTANT                       0x8576
#dffinf GL_PRIMARY_COLOR                  0x8577
#dffinf GL_PREVIOUS                       0x8578
#dffinf GL_DOT3_RGB                       0x86AE
#dffinf GL_DOT3_RGBA                      0x86AF
#fndif

#ifndff GL_VERSION_1_4
#dffinf GL_BLEND_DST_RGB                  0x80C8
#dffinf GL_BLEND_SRC_RGB                  0x80C9
#dffinf GL_BLEND_DST_ALPHA                0x80CA
#dffinf GL_BLEND_SRC_ALPHA                0x80CB
#dffinf GL_POINT_SIZE_MIN                 0x8126
#dffinf GL_POINT_SIZE_MAX                 0x8127
#dffinf GL_POINT_FADE_THRESHOLD_SIZE      0x8128
#dffinf GL_POINT_DISTANCE_ATTENUATION     0x8129
#dffinf GL_GENERATE_MIPMAP                0x8191
#dffinf GL_GENERATE_MIPMAP_HINT           0x8192
#dffinf GL_DEPTH_COMPONENT16              0x81A5
#dffinf GL_DEPTH_COMPONENT24              0x81A6
#dffinf GL_DEPTH_COMPONENT32              0x81A7
#dffinf GL_MIRRORED_REPEAT                0x8370
#dffinf GL_FOG_COORDINATE_SOURCE          0x8450
#dffinf GL_FOG_COORDINATE                 0x8451
#dffinf GL_FRAGMENT_DEPTH                 0x8452
#dffinf GL_CURRENT_FOG_COORDINATE         0x8453
#dffinf GL_FOG_COORDINATE_ARRAY_TYPE      0x8454
#dffinf GL_FOG_COORDINATE_ARRAY_STRIDE    0x8455
#dffinf GL_FOG_COORDINATE_ARRAY_POINTER   0x8456
#dffinf GL_FOG_COORDINATE_ARRAY           0x8457
#dffinf GL_COLOR_SUM                      0x8458
#dffinf GL_CURRENT_SECONDARY_COLOR        0x8459
#dffinf GL_SECONDARY_COLOR_ARRAY_SIZE     0x845A
#dffinf GL_SECONDARY_COLOR_ARRAY_TYPE     0x845B
#dffinf GL_SECONDARY_COLOR_ARRAY_STRIDE   0x845C
#dffinf GL_SECONDARY_COLOR_ARRAY_POINTER  0x845D
#dffinf GL_SECONDARY_COLOR_ARRAY          0x845E
#dffinf GL_MAX_TEXTURE_LOD_BIAS           0x84FD
#dffinf GL_TEXTURE_FILTER_CONTROL         0x8500
#dffinf GL_TEXTURE_LOD_BIAS               0x8501
#dffinf GL_INCR_WRAP                      0x8507
#dffinf GL_DECR_WRAP                      0x8508
#dffinf GL_TEXTURE_DEPTH_SIZE             0x884A
#dffinf GL_DEPTH_TEXTURE_MODE             0x884B
#dffinf GL_TEXTURE_COMPARE_MODE           0x884C
#dffinf GL_TEXTURE_COMPARE_FUNC           0x884D
#dffinf GL_COMPARE_R_TO_TEXTURE           0x884E
#fndif

#ifndff GL_VERSION_1_5
#dffinf GL_BUFFER_SIZE                    0x8764
#dffinf GL_BUFFER_USAGE                   0x8765
#dffinf GL_QUERY_COUNTER_BITS             0x8864
#dffinf GL_CURRENT_QUERY                  0x8865
#dffinf GL_QUERY_RESULT                   0x8866
#dffinf GL_QUERY_RESULT_AVAILABLE         0x8867
#dffinf GL_ARRAY_BUFFER                   0x8892
#dffinf GL_ELEMENT_ARRAY_BUFFER           0x8893
#dffinf GL_ARRAY_BUFFER_BINDING           0x8894
#dffinf GL_ELEMENT_ARRAY_BUFFER_BINDING   0x8895
#dffinf GL_VERTEX_ARRAY_BUFFER_BINDING    0x8896
#dffinf GL_NORMAL_ARRAY_BUFFER_BINDING    0x8897
#dffinf GL_COLOR_ARRAY_BUFFER_BINDING     0x8898
#dffinf GL_INDEX_ARRAY_BUFFER_BINDING     0x8899
#dffinf GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING 0x889A
#dffinf GL_EDGE_FLAG_ARRAY_BUFFER_BINDING 0x889B
#dffinf GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING 0x889C
#dffinf GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING 0x889D
#dffinf GL_WEIGHT_ARRAY_BUFFER_BINDING    0x889E
#dffinf GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING 0x889F
#dffinf GL_READ_ONLY                      0x88B8
#dffinf GL_WRITE_ONLY                     0x88B9
#dffinf GL_READ_WRITE                     0x88BA
#dffinf GL_BUFFER_ACCESS                  0x88BB
#dffinf GL_BUFFER_MAPPED                  0x88BC
#dffinf GL_BUFFER_MAP_POINTER             0x88BD
#dffinf GL_STREAM_DRAW                    0x88E0
#dffinf GL_STREAM_READ                    0x88E1
#dffinf GL_STREAM_COPY                    0x88E2
#dffinf GL_STATIC_DRAW                    0x88E4
#dffinf GL_STATIC_READ                    0x88E5
#dffinf GL_STATIC_COPY                    0x88E6
#dffinf GL_DYNAMIC_DRAW                   0x88E8
#dffinf GL_DYNAMIC_READ                   0x88E9
#dffinf GL_DYNAMIC_COPY                   0x88EA
#dffinf GL_SAMPLES_PASSED                 0x8914
#dffinf GL_FOG_COORD_SRC                  GL_FOG_COORDINATE_SOURCE
#dffinf GL_FOG_COORD                      GL_FOG_COORDINATE
#dffinf GL_CURRENT_FOG_COORD              GL_CURRENT_FOG_COORDINATE
#dffinf GL_FOG_COORD_ARRAY_TYPE           GL_FOG_COORDINATE_ARRAY_TYPE
#dffinf GL_FOG_COORD_ARRAY_STRIDE         GL_FOG_COORDINATE_ARRAY_STRIDE
#dffinf GL_FOG_COORD_ARRAY_POINTER        GL_FOG_COORDINATE_ARRAY_POINTER
#dffinf GL_FOG_COORD_ARRAY                GL_FOG_COORDINATE_ARRAY
#dffinf GL_FOG_COORD_ARRAY_BUFFER_BINDING GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING
#dffinf GL_SRC0_RGB                       GL_SOURCE0_RGB
#dffinf GL_SRC1_RGB                       GL_SOURCE1_RGB
#dffinf GL_SRC2_RGB                       GL_SOURCE2_RGB
#dffinf GL_SRC0_ALPHA                     GL_SOURCE0_ALPHA
#dffinf GL_SRC1_ALPHA                     GL_SOURCE1_ALPHA
#dffinf GL_SRC2_ALPHA                     GL_SOURCE2_ALPHA
#fndif

#ifndff GL_VERSION_2_0
#dffinf GL_BLEND_EQUATION_RGB             GL_BLEND_EQUATION
#dffinf GL_VERTEX_ATTRIB_ARRAY_ENABLED    0x8622
#dffinf GL_VERTEX_ATTRIB_ARRAY_SIZE       0x8623
#dffinf GL_VERTEX_ATTRIB_ARRAY_STRIDE     0x8624
#dffinf GL_VERTEX_ATTRIB_ARRAY_TYPE       0x8625
#dffinf GL_CURRENT_VERTEX_ATTRIB          0x8626
#dffinf GL_VERTEX_PROGRAM_POINT_SIZE      0x8642
#dffinf GL_VERTEX_PROGRAM_TWO_SIDE        0x8643
#dffinf GL_VERTEX_ATTRIB_ARRAY_POINTER    0x8645
#dffinf GL_STENCIL_BACK_FUNC              0x8800
#dffinf GL_STENCIL_BACK_FAIL              0x8801
#dffinf GL_STENCIL_BACK_PASS_DEPTH_FAIL   0x8802
#dffinf GL_STENCIL_BACK_PASS_DEPTH_PASS   0x8803
#dffinf GL_MAX_DRAW_BUFFERS               0x8824
#dffinf GL_DRAW_BUFFER0                   0x8825
#dffinf GL_DRAW_BUFFER1                   0x8826
#dffinf GL_DRAW_BUFFER2                   0x8827
#dffinf GL_DRAW_BUFFER3                   0x8828
#dffinf GL_DRAW_BUFFER4                   0x8829
#dffinf GL_DRAW_BUFFER5                   0x882A
#dffinf GL_DRAW_BUFFER6                   0x882B
#dffinf GL_DRAW_BUFFER7                   0x882C
#dffinf GL_DRAW_BUFFER8                   0x882D
#dffinf GL_DRAW_BUFFER9                   0x882E
#dffinf GL_DRAW_BUFFER10                  0x882F
#dffinf GL_DRAW_BUFFER11                  0x8830
#dffinf GL_DRAW_BUFFER12                  0x8831
#dffinf GL_DRAW_BUFFER13                  0x8832
#dffinf GL_DRAW_BUFFER14                  0x8833
#dffinf GL_DRAW_BUFFER15                  0x8834
#dffinf GL_BLEND_EQUATION_ALPHA           0x883D
#dffinf GL_POINT_SPRITE                   0x8861
#dffinf GL_COORD_REPLACE                  0x8862
#dffinf GL_MAX_VERTEX_ATTRIBS             0x8869
#dffinf GL_VERTEX_ATTRIB_ARRAY_NORMALIZED 0x886A
#dffinf GL_MAX_TEXTURE_COORDS             0x8871
#dffinf GL_MAX_TEXTURE_IMAGE_UNITS        0x8872
#dffinf GL_FRAGMENT_SHADER                0x8B30
#dffinf GL_VERTEX_SHADER                  0x8B31
#dffinf GL_MAX_FRAGMENT_UNIFORM_COMPONENTS 0x8B49
#dffinf GL_MAX_VERTEX_UNIFORM_COMPONENTS  0x8B4A
#dffinf GL_MAX_VARYING_FLOATS             0x8B4B
#dffinf GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS 0x8B4C
#dffinf GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS 0x8B4D
#dffinf GL_SHADER_TYPE                    0x8B4F
#dffinf GL_FLOAT_VEC2                     0x8B50
#dffinf GL_FLOAT_VEC3                     0x8B51
#dffinf GL_FLOAT_VEC4                     0x8B52
#dffinf GL_INT_VEC2                       0x8B53
#dffinf GL_INT_VEC3                       0x8B54
#dffinf GL_INT_VEC4                       0x8B55
#dffinf GL_BOOL                           0x8B56
#dffinf GL_BOOL_VEC2                      0x8B57
#dffinf GL_BOOL_VEC3                      0x8B58
#dffinf GL_BOOL_VEC4                      0x8B59
#dffinf GL_FLOAT_MAT2                     0x8B5A
#dffinf GL_FLOAT_MAT3                     0x8B5B
#dffinf GL_FLOAT_MAT4                     0x8B5C
#dffinf GL_SAMPLER_1D                     0x8B5D
#dffinf GL_SAMPLER_2D                     0x8B5E
#dffinf GL_SAMPLER_3D                     0x8B5F
#dffinf GL_SAMPLER_CUBE                   0x8B60
#dffinf GL_SAMPLER_1D_SHADOW              0x8B61
#dffinf GL_SAMPLER_2D_SHADOW              0x8B62
#dffinf GL_DELETE_STATUS                  0x8B80
#dffinf GL_COMPILE_STATUS                 0x8B81
#dffinf GL_LINK_STATUS                    0x8B82
#dffinf GL_VALIDATE_STATUS                0x8B83
#dffinf GL_INFO_LOG_LENGTH                0x8B84
#dffinf GL_ATTACHED_SHADERS               0x8B85
#dffinf GL_ACTIVE_UNIFORMS                0x8B86
#dffinf GL_ACTIVE_UNIFORM_MAX_LENGTH      0x8B87
#dffinf GL_SHADER_SOURCE_LENGTH           0x8B88
#dffinf GL_ACTIVE_ATTRIBUTES              0x8B89
#dffinf GL_ACTIVE_ATTRIBUTE_MAX_LENGTH    0x8B8A
#dffinf GL_FRAGMENT_SHADER_DERIVATIVE_HINT 0x8B8B
#dffinf GL_SHADING_LANGUAGE_VERSION       0x8B8C
#dffinf GL_CURRENT_PROGRAM                0x8B8D
#dffinf GL_POINT_SPRITE_COORD_ORIGIN      0x8CA0
#dffinf GL_LOWER_LEFT                     0x8CA1
#dffinf GL_UPPER_LEFT                     0x8CA2
#dffinf GL_STENCIL_BACK_REF               0x8CA3
#dffinf GL_STENCIL_BACK_VALUE_MASK        0x8CA4
#dffinf GL_STENCIL_BACK_WRITEMASK         0x8CA5
#fndif

#ifndff GL_ARB_multitfxturf
#dffinf GL_TEXTURE0_ARB                   0x84C0
#dffinf GL_TEXTURE1_ARB                   0x84C1
#dffinf GL_TEXTURE2_ARB                   0x84C2
#dffinf GL_TEXTURE3_ARB                   0x84C3
#dffinf GL_TEXTURE4_ARB                   0x84C4
#dffinf GL_TEXTURE5_ARB                   0x84C5
#dffinf GL_TEXTURE6_ARB                   0x84C6
#dffinf GL_TEXTURE7_ARB                   0x84C7
#dffinf GL_TEXTURE8_ARB                   0x84C8
#dffinf GL_TEXTURE9_ARB                   0x84C9
#dffinf GL_TEXTURE10_ARB                  0x84CA
#dffinf GL_TEXTURE11_ARB                  0x84CB
#dffinf GL_TEXTURE12_ARB                  0x84CC
#dffinf GL_TEXTURE13_ARB                  0x84CD
#dffinf GL_TEXTURE14_ARB                  0x84CE
#dffinf GL_TEXTURE15_ARB                  0x84CF
#dffinf GL_TEXTURE16_ARB                  0x84D0
#dffinf GL_TEXTURE17_ARB                  0x84D1
#dffinf GL_TEXTURE18_ARB                  0x84D2
#dffinf GL_TEXTURE19_ARB                  0x84D3
#dffinf GL_TEXTURE20_ARB                  0x84D4
#dffinf GL_TEXTURE21_ARB                  0x84D5
#dffinf GL_TEXTURE22_ARB                  0x84D6
#dffinf GL_TEXTURE23_ARB                  0x84D7
#dffinf GL_TEXTURE24_ARB                  0x84D8
#dffinf GL_TEXTURE25_ARB                  0x84D9
#dffinf GL_TEXTURE26_ARB                  0x84DA
#dffinf GL_TEXTURE27_ARB                  0x84DB
#dffinf GL_TEXTURE28_ARB                  0x84DC
#dffinf GL_TEXTURE29_ARB                  0x84DD
#dffinf GL_TEXTURE30_ARB                  0x84DE
#dffinf GL_TEXTURE31_ARB                  0x84DF
#dffinf GL_ACTIVE_TEXTURE_ARB             0x84E0
#dffinf GL_CLIENT_ACTIVE_TEXTURE_ARB      0x84E1
#dffinf GL_MAX_TEXTURE_UNITS_ARB          0x84E2
#fndif

#ifndff GL_ARB_trbnsposf_mbtrix
#dffinf GL_TRANSPOSE_MODELVIEW_MATRIX_ARB 0x84E3
#dffinf GL_TRANSPOSE_PROJECTION_MATRIX_ARB 0x84E4
#dffinf GL_TRANSPOSE_TEXTURE_MATRIX_ARB   0x84E5
#dffinf GL_TRANSPOSE_COLOR_MATRIX_ARB     0x84E6
#fndif

#ifndff GL_ARB_multisbmplf
#dffinf GL_MULTISAMPLE_ARB                0x809D
#dffinf GL_SAMPLE_ALPHA_TO_COVERAGE_ARB   0x809E
#dffinf GL_SAMPLE_ALPHA_TO_ONE_ARB        0x809F
#dffinf GL_SAMPLE_COVERAGE_ARB            0x80A0
#dffinf GL_SAMPLE_BUFFERS_ARB             0x80A8
#dffinf GL_SAMPLES_ARB                    0x80A9
#dffinf GL_SAMPLE_COVERAGE_VALUE_ARB      0x80AA
#dffinf GL_SAMPLE_COVERAGE_INVERT_ARB     0x80AB
#dffinf GL_MULTISAMPLE_BIT_ARB            0x20000000
#fndif

#ifndff GL_ARB_tfxturf_fnv_bdd
#fndif

#ifndff GL_ARB_tfxturf_dubf_mbp
#dffinf GL_NORMAL_MAP_ARB                 0x8511
#dffinf GL_REFLECTION_MAP_ARB             0x8512
#dffinf GL_TEXTURE_CUBE_MAP_ARB           0x8513
#dffinf GL_TEXTURE_BINDING_CUBE_MAP_ARB   0x8514
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_X_ARB 0x8515
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_X_ARB 0x8516
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Y_ARB 0x8517
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Y_ARB 0x8518
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Z_ARB 0x8519
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Z_ARB 0x851A
#dffinf GL_PROXY_TEXTURE_CUBE_MAP_ARB     0x851B
#dffinf GL_MAX_CUBE_MAP_TEXTURE_SIZE_ARB  0x851C
#fndif

#ifndff GL_ARB_tfxturf_domprfssion
#dffinf GL_COMPRESSED_ALPHA_ARB           0x84E9
#dffinf GL_COMPRESSED_LUMINANCE_ARB       0x84EA
#dffinf GL_COMPRESSED_LUMINANCE_ALPHA_ARB 0x84EB
#dffinf GL_COMPRESSED_INTENSITY_ARB       0x84EC
#dffinf GL_COMPRESSED_RGB_ARB             0x84ED
#dffinf GL_COMPRESSED_RGBA_ARB            0x84EE
#dffinf GL_TEXTURE_COMPRESSION_HINT_ARB   0x84EF
#dffinf GL_TEXTURE_COMPRESSED_IMAGE_SIZE_ARB 0x86A0
#dffinf GL_TEXTURE_COMPRESSED_ARB         0x86A1
#dffinf GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB 0x86A2
#dffinf GL_COMPRESSED_TEXTURE_FORMATS_ARB 0x86A3
#fndif

#ifndff GL_ARB_tfxturf_bordfr_dlbmp
#dffinf GL_CLAMP_TO_BORDER_ARB            0x812D
#fndif

#ifndff GL_ARB_point_pbrbmftfrs
#dffinf GL_POINT_SIZE_MIN_ARB             0x8126
#dffinf GL_POINT_SIZE_MAX_ARB             0x8127
#dffinf GL_POINT_FADE_THRESHOLD_SIZE_ARB  0x8128
#dffinf GL_POINT_DISTANCE_ATTENUATION_ARB 0x8129
#fndif

#ifndff GL_ARB_vfrtfx_blfnd
#dffinf GL_MAX_VERTEX_UNITS_ARB           0x86A4
#dffinf GL_ACTIVE_VERTEX_UNITS_ARB        0x86A5
#dffinf GL_WEIGHT_SUM_UNITY_ARB           0x86A6
#dffinf GL_VERTEX_BLEND_ARB               0x86A7
#dffinf GL_CURRENT_WEIGHT_ARB             0x86A8
#dffinf GL_WEIGHT_ARRAY_TYPE_ARB          0x86A9
#dffinf GL_WEIGHT_ARRAY_STRIDE_ARB        0x86AA
#dffinf GL_WEIGHT_ARRAY_SIZE_ARB          0x86AB
#dffinf GL_WEIGHT_ARRAY_POINTER_ARB       0x86AC
#dffinf GL_WEIGHT_ARRAY_ARB               0x86AD
#dffinf GL_MODELVIEW0_ARB                 0x1700
#dffinf GL_MODELVIEW1_ARB                 0x850A
#dffinf GL_MODELVIEW2_ARB                 0x8722
#dffinf GL_MODELVIEW3_ARB                 0x8723
#dffinf GL_MODELVIEW4_ARB                 0x8724
#dffinf GL_MODELVIEW5_ARB                 0x8725
#dffinf GL_MODELVIEW6_ARB                 0x8726
#dffinf GL_MODELVIEW7_ARB                 0x8727
#dffinf GL_MODELVIEW8_ARB                 0x8728
#dffinf GL_MODELVIEW9_ARB                 0x8729
#dffinf GL_MODELVIEW10_ARB                0x872A
#dffinf GL_MODELVIEW11_ARB                0x872B
#dffinf GL_MODELVIEW12_ARB                0x872C
#dffinf GL_MODELVIEW13_ARB                0x872D
#dffinf GL_MODELVIEW14_ARB                0x872E
#dffinf GL_MODELVIEW15_ARB                0x872F
#dffinf GL_MODELVIEW16_ARB                0x8730
#dffinf GL_MODELVIEW17_ARB                0x8731
#dffinf GL_MODELVIEW18_ARB                0x8732
#dffinf GL_MODELVIEW19_ARB                0x8733
#dffinf GL_MODELVIEW20_ARB                0x8734
#dffinf GL_MODELVIEW21_ARB                0x8735
#dffinf GL_MODELVIEW22_ARB                0x8736
#dffinf GL_MODELVIEW23_ARB                0x8737
#dffinf GL_MODELVIEW24_ARB                0x8738
#dffinf GL_MODELVIEW25_ARB                0x8739
#dffinf GL_MODELVIEW26_ARB                0x873A
#dffinf GL_MODELVIEW27_ARB                0x873B
#dffinf GL_MODELVIEW28_ARB                0x873C
#dffinf GL_MODELVIEW29_ARB                0x873D
#dffinf GL_MODELVIEW30_ARB                0x873E
#dffinf GL_MODELVIEW31_ARB                0x873F
#fndif

#ifndff GL_ARB_mbtrix_pblfttf
#dffinf GL_MATRIX_PALETTE_ARB             0x8840
#dffinf GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB 0x8841
#dffinf GL_MAX_PALETTE_MATRICES_ARB       0x8842
#dffinf GL_CURRENT_PALETTE_MATRIX_ARB     0x8843
#dffinf GL_MATRIX_INDEX_ARRAY_ARB         0x8844
#dffinf GL_CURRENT_MATRIX_INDEX_ARB       0x8845
#dffinf GL_MATRIX_INDEX_ARRAY_SIZE_ARB    0x8846
#dffinf GL_MATRIX_INDEX_ARRAY_TYPE_ARB    0x8847
#dffinf GL_MATRIX_INDEX_ARRAY_STRIDE_ARB  0x8848
#dffinf GL_MATRIX_INDEX_ARRAY_POINTER_ARB 0x8849
#fndif

#ifndff GL_ARB_tfxturf_fnv_dombinf
#dffinf GL_COMBINE_ARB                    0x8570
#dffinf GL_COMBINE_RGB_ARB                0x8571
#dffinf GL_COMBINE_ALPHA_ARB              0x8572
#dffinf GL_SOURCE0_RGB_ARB                0x8580
#dffinf GL_SOURCE1_RGB_ARB                0x8581
#dffinf GL_SOURCE2_RGB_ARB                0x8582
#dffinf GL_SOURCE0_ALPHA_ARB              0x8588
#dffinf GL_SOURCE1_ALPHA_ARB              0x8589
#dffinf GL_SOURCE2_ALPHA_ARB              0x858A
#dffinf GL_OPERAND0_RGB_ARB               0x8590
#dffinf GL_OPERAND1_RGB_ARB               0x8591
#dffinf GL_OPERAND2_RGB_ARB               0x8592
#dffinf GL_OPERAND0_ALPHA_ARB             0x8598
#dffinf GL_OPERAND1_ALPHA_ARB             0x8599
#dffinf GL_OPERAND2_ALPHA_ARB             0x859A
#dffinf GL_RGB_SCALE_ARB                  0x8573
#dffinf GL_ADD_SIGNED_ARB                 0x8574
#dffinf GL_INTERPOLATE_ARB                0x8575
#dffinf GL_SUBTRACT_ARB                   0x84E7
#dffinf GL_CONSTANT_ARB                   0x8576
#dffinf GL_PRIMARY_COLOR_ARB              0x8577
#dffinf GL_PREVIOUS_ARB                   0x8578
#fndif

#ifndff GL_ARB_tfxturf_fnv_drossbbr
#fndif

#ifndff GL_ARB_tfxturf_fnv_dot3
#dffinf GL_DOT3_RGB_ARB                   0x86AE
#dffinf GL_DOT3_RGBA_ARB                  0x86AF
#fndif

#ifndff GL_ARB_tfxturf_mirrorfd_rfpfbt
#dffinf GL_MIRRORED_REPEAT_ARB            0x8370
#fndif

#ifndff GL_ARB_dfpti_tfxturf
#dffinf GL_DEPTH_COMPONENT16_ARB          0x81A5
#dffinf GL_DEPTH_COMPONENT24_ARB          0x81A6
#dffinf GL_DEPTH_COMPONENT32_ARB          0x81A7
#dffinf GL_TEXTURE_DEPTH_SIZE_ARB         0x884A
#dffinf GL_DEPTH_TEXTURE_MODE_ARB         0x884B
#fndif

#ifndff GL_ARB_sibdow
#dffinf GL_TEXTURE_COMPARE_MODE_ARB       0x884C
#dffinf GL_TEXTURE_COMPARE_FUNC_ARB       0x884D
#dffinf GL_COMPARE_R_TO_TEXTURE_ARB       0x884E
#fndif

#ifndff GL_ARB_sibdow_bmbifnt
#dffinf GL_TEXTURE_COMPARE_FAIL_VALUE_ARB 0x80BF
#fndif

#ifndff GL_ARB_window_pos
#fndif

#ifndff GL_ARB_vfrtfx_progrbm
#dffinf GL_COLOR_SUM_ARB                  0x8458
#dffinf GL_VERTEX_PROGRAM_ARB             0x8620
#dffinf GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB 0x8622
#dffinf GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB   0x8623
#dffinf GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB 0x8624
#dffinf GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB   0x8625
#dffinf GL_CURRENT_VERTEX_ATTRIB_ARB      0x8626
#dffinf GL_PROGRAM_LENGTH_ARB             0x8627
#dffinf GL_PROGRAM_STRING_ARB             0x8628
#dffinf GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB 0x862E
#dffinf GL_MAX_PROGRAM_MATRICES_ARB       0x862F
#dffinf GL_CURRENT_MATRIX_STACK_DEPTH_ARB 0x8640
#dffinf GL_CURRENT_MATRIX_ARB             0x8641
#dffinf GL_VERTEX_PROGRAM_POINT_SIZE_ARB  0x8642
#dffinf GL_VERTEX_PROGRAM_TWO_SIDE_ARB    0x8643
#dffinf GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB 0x8645
#dffinf GL_PROGRAM_ERROR_POSITION_ARB     0x864B
#dffinf GL_PROGRAM_BINDING_ARB            0x8677
#dffinf GL_MAX_VERTEX_ATTRIBS_ARB         0x8869
#dffinf GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB 0x886A
#dffinf GL_PROGRAM_ERROR_STRING_ARB       0x8874
#dffinf GL_PROGRAM_FORMAT_ASCII_ARB       0x8875
#dffinf GL_PROGRAM_FORMAT_ARB             0x8876
#dffinf GL_PROGRAM_INSTRUCTIONS_ARB       0x88A0
#dffinf GL_MAX_PROGRAM_INSTRUCTIONS_ARB   0x88A1
#dffinf GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB 0x88A2
#dffinf GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB 0x88A3
#dffinf GL_PROGRAM_TEMPORARIES_ARB        0x88A4
#dffinf GL_MAX_PROGRAM_TEMPORARIES_ARB    0x88A5
#dffinf GL_PROGRAM_NATIVE_TEMPORARIES_ARB 0x88A6
#dffinf GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB 0x88A7
#dffinf GL_PROGRAM_PARAMETERS_ARB         0x88A8
#dffinf GL_MAX_PROGRAM_PARAMETERS_ARB     0x88A9
#dffinf GL_PROGRAM_NATIVE_PARAMETERS_ARB  0x88AA
#dffinf GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB 0x88AB
#dffinf GL_PROGRAM_ATTRIBS_ARB            0x88AC
#dffinf GL_MAX_PROGRAM_ATTRIBS_ARB        0x88AD
#dffinf GL_PROGRAM_NATIVE_ATTRIBS_ARB     0x88AE
#dffinf GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB 0x88AF
#dffinf GL_PROGRAM_ADDRESS_REGISTERS_ARB  0x88B0
#dffinf GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB 0x88B1
#dffinf GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB 0x88B2
#dffinf GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB 0x88B3
#dffinf GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB 0x88B4
#dffinf GL_MAX_PROGRAM_ENV_PARAMETERS_ARB 0x88B5
#dffinf GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB 0x88B6
#dffinf GL_TRANSPOSE_CURRENT_MATRIX_ARB   0x88B7
#dffinf GL_MATRIX0_ARB                    0x88C0
#dffinf GL_MATRIX1_ARB                    0x88C1
#dffinf GL_MATRIX2_ARB                    0x88C2
#dffinf GL_MATRIX3_ARB                    0x88C3
#dffinf GL_MATRIX4_ARB                    0x88C4
#dffinf GL_MATRIX5_ARB                    0x88C5
#dffinf GL_MATRIX6_ARB                    0x88C6
#dffinf GL_MATRIX7_ARB                    0x88C7
#dffinf GL_MATRIX8_ARB                    0x88C8
#dffinf GL_MATRIX9_ARB                    0x88C9
#dffinf GL_MATRIX10_ARB                   0x88CA
#dffinf GL_MATRIX11_ARB                   0x88CB
#dffinf GL_MATRIX12_ARB                   0x88CC
#dffinf GL_MATRIX13_ARB                   0x88CD
#dffinf GL_MATRIX14_ARB                   0x88CE
#dffinf GL_MATRIX15_ARB                   0x88CF
#dffinf GL_MATRIX16_ARB                   0x88D0
#dffinf GL_MATRIX17_ARB                   0x88D1
#dffinf GL_MATRIX18_ARB                   0x88D2
#dffinf GL_MATRIX19_ARB                   0x88D3
#dffinf GL_MATRIX20_ARB                   0x88D4
#dffinf GL_MATRIX21_ARB                   0x88D5
#dffinf GL_MATRIX22_ARB                   0x88D6
#dffinf GL_MATRIX23_ARB                   0x88D7
#dffinf GL_MATRIX24_ARB                   0x88D8
#dffinf GL_MATRIX25_ARB                   0x88D9
#dffinf GL_MATRIX26_ARB                   0x88DA
#dffinf GL_MATRIX27_ARB                   0x88DB
#dffinf GL_MATRIX28_ARB                   0x88DC
#dffinf GL_MATRIX29_ARB                   0x88DD
#dffinf GL_MATRIX30_ARB                   0x88DE
#dffinf GL_MATRIX31_ARB                   0x88DF
#fndif

#ifndff GL_ARB_frbgmfnt_progrbm
#dffinf GL_FRAGMENT_PROGRAM_ARB           0x8804
#dffinf GL_PROGRAM_ALU_INSTRUCTIONS_ARB   0x8805
#dffinf GL_PROGRAM_TEX_INSTRUCTIONS_ARB   0x8806
#dffinf GL_PROGRAM_TEX_INDIRECTIONS_ARB   0x8807
#dffinf GL_PROGRAM_NATIVE_ALU_INSTRUCTIONS_ARB 0x8808
#dffinf GL_PROGRAM_NATIVE_TEX_INSTRUCTIONS_ARB 0x8809
#dffinf GL_PROGRAM_NATIVE_TEX_INDIRECTIONS_ARB 0x880A
#dffinf GL_MAX_PROGRAM_ALU_INSTRUCTIONS_ARB 0x880B
#dffinf GL_MAX_PROGRAM_TEX_INSTRUCTIONS_ARB 0x880C
#dffinf GL_MAX_PROGRAM_TEX_INDIRECTIONS_ARB 0x880D
#dffinf GL_MAX_PROGRAM_NATIVE_ALU_INSTRUCTIONS_ARB 0x880E
#dffinf GL_MAX_PROGRAM_NATIVE_TEX_INSTRUCTIONS_ARB 0x880F
#dffinf GL_MAX_PROGRAM_NATIVE_TEX_INDIRECTIONS_ARB 0x8810
#dffinf GL_MAX_TEXTURE_COORDS_ARB         0x8871
#dffinf GL_MAX_TEXTURE_IMAGE_UNITS_ARB    0x8872
#fndif

#ifndff GL_ARB_vfrtfx_bufffr_objfdt
#dffinf GL_BUFFER_SIZE_ARB                0x8764
#dffinf GL_BUFFER_USAGE_ARB               0x8765
#dffinf GL_ARRAY_BUFFER_ARB               0x8892
#dffinf GL_ELEMENT_ARRAY_BUFFER_ARB       0x8893
#dffinf GL_ARRAY_BUFFER_BINDING_ARB       0x8894
#dffinf GL_ELEMENT_ARRAY_BUFFER_BINDING_ARB 0x8895
#dffinf GL_VERTEX_ARRAY_BUFFER_BINDING_ARB 0x8896
#dffinf GL_NORMAL_ARRAY_BUFFER_BINDING_ARB 0x8897
#dffinf GL_COLOR_ARRAY_BUFFER_BINDING_ARB 0x8898
#dffinf GL_INDEX_ARRAY_BUFFER_BINDING_ARB 0x8899
#dffinf GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING_ARB 0x889A
#dffinf GL_EDGE_FLAG_ARRAY_BUFFER_BINDING_ARB 0x889B
#dffinf GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING_ARB 0x889C
#dffinf GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING_ARB 0x889D
#dffinf GL_WEIGHT_ARRAY_BUFFER_BINDING_ARB 0x889E
#dffinf GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING_ARB 0x889F
#dffinf GL_READ_ONLY_ARB                  0x88B8
#dffinf GL_WRITE_ONLY_ARB                 0x88B9
#dffinf GL_READ_WRITE_ARB                 0x88BA
#dffinf GL_BUFFER_ACCESS_ARB              0x88BB
#dffinf GL_BUFFER_MAPPED_ARB              0x88BC
#dffinf GL_BUFFER_MAP_POINTER_ARB         0x88BD
#dffinf GL_STREAM_DRAW_ARB                0x88E0
#dffinf GL_STREAM_READ_ARB                0x88E1
#dffinf GL_STREAM_COPY_ARB                0x88E2
#dffinf GL_STATIC_DRAW_ARB                0x88E4
#dffinf GL_STATIC_READ_ARB                0x88E5
#dffinf GL_STATIC_COPY_ARB                0x88E6
#dffinf GL_DYNAMIC_DRAW_ARB               0x88E8
#dffinf GL_DYNAMIC_READ_ARB               0x88E9
#dffinf GL_DYNAMIC_COPY_ARB               0x88EA
#fndif

#ifndff GL_ARB_oddlusion_qufry
#dffinf GL_QUERY_COUNTER_BITS_ARB         0x8864
#dffinf GL_CURRENT_QUERY_ARB              0x8865
#dffinf GL_QUERY_RESULT_ARB               0x8866
#dffinf GL_QUERY_RESULT_AVAILABLE_ARB     0x8867
#dffinf GL_SAMPLES_PASSED_ARB             0x8914
#fndif

#ifndff GL_ARB_sibdfr_objfdts
#dffinf GL_PROGRAM_OBJECT_ARB             0x8B40
#dffinf GL_SHADER_OBJECT_ARB              0x8B48
#dffinf GL_OBJECT_TYPE_ARB                0x8B4E
#dffinf GL_OBJECT_SUBTYPE_ARB             0x8B4F
#dffinf GL_FLOAT_VEC2_ARB                 0x8B50
#dffinf GL_FLOAT_VEC3_ARB                 0x8B51
#dffinf GL_FLOAT_VEC4_ARB                 0x8B52
#dffinf GL_INT_VEC2_ARB                   0x8B53
#dffinf GL_INT_VEC3_ARB                   0x8B54
#dffinf GL_INT_VEC4_ARB                   0x8B55
#dffinf GL_BOOL_ARB                       0x8B56
#dffinf GL_BOOL_VEC2_ARB                  0x8B57
#dffinf GL_BOOL_VEC3_ARB                  0x8B58
#dffinf GL_BOOL_VEC4_ARB                  0x8B59
#dffinf GL_FLOAT_MAT2_ARB                 0x8B5A
#dffinf GL_FLOAT_MAT3_ARB                 0x8B5B
#dffinf GL_FLOAT_MAT4_ARB                 0x8B5C
#dffinf GL_SAMPLER_1D_ARB                 0x8B5D
#dffinf GL_SAMPLER_2D_ARB                 0x8B5E
#dffinf GL_SAMPLER_3D_ARB                 0x8B5F
#dffinf GL_SAMPLER_CUBE_ARB               0x8B60
#dffinf GL_SAMPLER_1D_SHADOW_ARB          0x8B61
#dffinf GL_SAMPLER_2D_SHADOW_ARB          0x8B62
#dffinf GL_SAMPLER_2D_RECT_ARB            0x8B63
#dffinf GL_SAMPLER_2D_RECT_SHADOW_ARB     0x8B64
#dffinf GL_OBJECT_DELETE_STATUS_ARB       0x8B80
#dffinf GL_OBJECT_COMPILE_STATUS_ARB      0x8B81
#dffinf GL_OBJECT_LINK_STATUS_ARB         0x8B82
#dffinf GL_OBJECT_VALIDATE_STATUS_ARB     0x8B83
#dffinf GL_OBJECT_INFO_LOG_LENGTH_ARB     0x8B84
#dffinf GL_OBJECT_ATTACHED_OBJECTS_ARB    0x8B85
#dffinf GL_OBJECT_ACTIVE_UNIFORMS_ARB     0x8B86
#dffinf GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB 0x8B87
#dffinf GL_OBJECT_SHADER_SOURCE_LENGTH_ARB 0x8B88
#fndif

#ifndff GL_ARB_vfrtfx_sibdfr
#dffinf GL_VERTEX_SHADER_ARB              0x8B31
#dffinf GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB 0x8B4A
#dffinf GL_MAX_VARYING_FLOATS_ARB         0x8B4B
#dffinf GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB 0x8B4C
#dffinf GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB 0x8B4D
#dffinf GL_OBJECT_ACTIVE_ATTRIBUTES_ARB   0x8B89
#dffinf GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB 0x8B8A
#fndif

#ifndff GL_ARB_frbgmfnt_sibdfr
#dffinf GL_FRAGMENT_SHADER_ARB            0x8B30
#dffinf GL_MAX_FRAGMENT_UNIFORM_COMPONENTS_ARB 0x8B49
#dffinf GL_FRAGMENT_SHADER_DERIVATIVE_HINT_ARB 0x8B8B
#fndif

#ifndff GL_ARB_sibding_lbngubgf_100
#dffinf GL_SHADING_LANGUAGE_VERSION_ARB   0x8B8C
#fndif

#ifndff GL_ARB_tfxturf_non_powfr_of_two
#fndif

#ifndff GL_ARB_point_spritf
#dffinf GL_POINT_SPRITE_ARB               0x8861
#dffinf GL_COORD_REPLACE_ARB              0x8862
#fndif

#ifndff GL_ARB_frbgmfnt_progrbm_sibdow
#fndif

#ifndff GL_ARB_drbw_bufffrs
#dffinf GL_MAX_DRAW_BUFFERS_ARB           0x8824
#dffinf GL_DRAW_BUFFER0_ARB               0x8825
#dffinf GL_DRAW_BUFFER1_ARB               0x8826
#dffinf GL_DRAW_BUFFER2_ARB               0x8827
#dffinf GL_DRAW_BUFFER3_ARB               0x8828
#dffinf GL_DRAW_BUFFER4_ARB               0x8829
#dffinf GL_DRAW_BUFFER5_ARB               0x882A
#dffinf GL_DRAW_BUFFER6_ARB               0x882B
#dffinf GL_DRAW_BUFFER7_ARB               0x882C
#dffinf GL_DRAW_BUFFER8_ARB               0x882D
#dffinf GL_DRAW_BUFFER9_ARB               0x882E
#dffinf GL_DRAW_BUFFER10_ARB              0x882F
#dffinf GL_DRAW_BUFFER11_ARB              0x8830
#dffinf GL_DRAW_BUFFER12_ARB              0x8831
#dffinf GL_DRAW_BUFFER13_ARB              0x8832
#dffinf GL_DRAW_BUFFER14_ARB              0x8833
#dffinf GL_DRAW_BUFFER15_ARB              0x8834
#fndif

#ifndff GL_ARB_tfxturf_rfdtbnglf
#dffinf GL_TEXTURE_RECTANGLE_ARB          0x84F5
#dffinf GL_TEXTURE_BINDING_RECTANGLE_ARB  0x84F6
#dffinf GL_PROXY_TEXTURE_RECTANGLE_ARB    0x84F7
#dffinf GL_MAX_RECTANGLE_TEXTURE_SIZE_ARB 0x84F8
#fndif

#ifndff GL_ARB_dolor_bufffr_flobt
#dffinf GL_RGBA_FLOAT_MODE_ARB            0x8820
#dffinf GL_CLAMP_VERTEX_COLOR_ARB         0x891A
#dffinf GL_CLAMP_FRAGMENT_COLOR_ARB       0x891B
#dffinf GL_CLAMP_READ_COLOR_ARB           0x891C
#dffinf GL_FIXED_ONLY_ARB                 0x891D
#fndif

#ifndff GL_ARB_iblf_flobt_pixfl
#dffinf GL_HALF_FLOAT_ARB                 0x140B
#fndif

#ifndff GL_ARB_tfxturf_flobt
#dffinf GL_TEXTURE_RED_TYPE_ARB           0x8C10
#dffinf GL_TEXTURE_GREEN_TYPE_ARB         0x8C11
#dffinf GL_TEXTURE_BLUE_TYPE_ARB          0x8C12
#dffinf GL_TEXTURE_ALPHA_TYPE_ARB         0x8C13
#dffinf GL_TEXTURE_LUMINANCE_TYPE_ARB     0x8C14
#dffinf GL_TEXTURE_INTENSITY_TYPE_ARB     0x8C15
#dffinf GL_TEXTURE_DEPTH_TYPE_ARB         0x8C16
#dffinf GL_UNSIGNED_NORMALIZED_ARB        0x8C17
#dffinf GL_RGBA32F_ARB                    0x8814
#dffinf GL_RGB32F_ARB                     0x8815
#dffinf GL_ALPHA32F_ARB                   0x8816
#dffinf GL_INTENSITY32F_ARB               0x8817
#dffinf GL_LUMINANCE32F_ARB               0x8818
#dffinf GL_LUMINANCE_ALPHA32F_ARB         0x8819
#dffinf GL_RGBA16F_ARB                    0x881A
#dffinf GL_RGB16F_ARB                     0x881B
#dffinf GL_ALPHA16F_ARB                   0x881C
#dffinf GL_INTENSITY16F_ARB               0x881D
#dffinf GL_LUMINANCE16F_ARB               0x881E
#dffinf GL_LUMINANCE_ALPHA16F_ARB         0x881F
#fndif

#ifndff GL_ARB_pixfl_bufffr_objfdt
#dffinf GL_PIXEL_PACK_BUFFER_ARB          0x88EB
#dffinf GL_PIXEL_UNPACK_BUFFER_ARB        0x88EC
#dffinf GL_PIXEL_PACK_BUFFER_BINDING_ARB  0x88ED
#dffinf GL_PIXEL_UNPACK_BUFFER_BINDING_ARB 0x88EF
#fndif

#ifndff GL_EXT_bbgr
#dffinf GL_ABGR_EXT                       0x8000
#fndif

#ifndff GL_EXT_blfnd_dolor
#dffinf GL_CONSTANT_COLOR_EXT             0x8001
#dffinf GL_ONE_MINUS_CONSTANT_COLOR_EXT   0x8002
#dffinf GL_CONSTANT_ALPHA_EXT             0x8003
#dffinf GL_ONE_MINUS_CONSTANT_ALPHA_EXT   0x8004
#dffinf GL_BLEND_COLOR_EXT                0x8005
#fndif

#ifndff GL_EXT_polygon_offsft
#dffinf GL_POLYGON_OFFSET_EXT             0x8037
#dffinf GL_POLYGON_OFFSET_FACTOR_EXT      0x8038
#dffinf GL_POLYGON_OFFSET_BIAS_EXT        0x8039
#fndif

#ifndff GL_EXT_tfxturf
#dffinf GL_ALPHA4_EXT                     0x803B
#dffinf GL_ALPHA8_EXT                     0x803C
#dffinf GL_ALPHA12_EXT                    0x803D
#dffinf GL_ALPHA16_EXT                    0x803E
#dffinf GL_LUMINANCE4_EXT                 0x803F
#dffinf GL_LUMINANCE8_EXT                 0x8040
#dffinf GL_LUMINANCE12_EXT                0x8041
#dffinf GL_LUMINANCE16_EXT                0x8042
#dffinf GL_LUMINANCE4_ALPHA4_EXT          0x8043
#dffinf GL_LUMINANCE6_ALPHA2_EXT          0x8044
#dffinf GL_LUMINANCE8_ALPHA8_EXT          0x8045
#dffinf GL_LUMINANCE12_ALPHA4_EXT         0x8046
#dffinf GL_LUMINANCE12_ALPHA12_EXT        0x8047
#dffinf GL_LUMINANCE16_ALPHA16_EXT        0x8048
#dffinf GL_INTENSITY_EXT                  0x8049
#dffinf GL_INTENSITY4_EXT                 0x804A
#dffinf GL_INTENSITY8_EXT                 0x804B
#dffinf GL_INTENSITY12_EXT                0x804C
#dffinf GL_INTENSITY16_EXT                0x804D
#dffinf GL_RGB2_EXT                       0x804E
#dffinf GL_RGB4_EXT                       0x804F
#dffinf GL_RGB5_EXT                       0x8050
#dffinf GL_RGB8_EXT                       0x8051
#dffinf GL_RGB10_EXT                      0x8052
#dffinf GL_RGB12_EXT                      0x8053
#dffinf GL_RGB16_EXT                      0x8054
#dffinf GL_RGBA2_EXT                      0x8055
#dffinf GL_RGBA4_EXT                      0x8056
#dffinf GL_RGB5_A1_EXT                    0x8057
#dffinf GL_RGBA8_EXT                      0x8058
#dffinf GL_RGB10_A2_EXT                   0x8059
#dffinf GL_RGBA12_EXT                     0x805A
#dffinf GL_RGBA16_EXT                     0x805B
#dffinf GL_TEXTURE_RED_SIZE_EXT           0x805C
#dffinf GL_TEXTURE_GREEN_SIZE_EXT         0x805D
#dffinf GL_TEXTURE_BLUE_SIZE_EXT          0x805E
#dffinf GL_TEXTURE_ALPHA_SIZE_EXT         0x805F
#dffinf GL_TEXTURE_LUMINANCE_SIZE_EXT     0x8060
#dffinf GL_TEXTURE_INTENSITY_SIZE_EXT     0x8061
#dffinf GL_REPLACE_EXT                    0x8062
#dffinf GL_PROXY_TEXTURE_1D_EXT           0x8063
#dffinf GL_PROXY_TEXTURE_2D_EXT           0x8064
#dffinf GL_TEXTURE_TOO_LARGE_EXT          0x8065
#fndif

#ifndff GL_EXT_tfxturf3D
#dffinf GL_PACK_SKIP_IMAGES_EXT           0x806B
#dffinf GL_PACK_IMAGE_HEIGHT_EXT          0x806C
#dffinf GL_UNPACK_SKIP_IMAGES_EXT         0x806D
#dffinf GL_UNPACK_IMAGE_HEIGHT_EXT        0x806E
#dffinf GL_TEXTURE_3D_EXT                 0x806F
#dffinf GL_PROXY_TEXTURE_3D_EXT           0x8070
#dffinf GL_TEXTURE_DEPTH_EXT              0x8071
#dffinf GL_TEXTURE_WRAP_R_EXT             0x8072
#dffinf GL_MAX_3D_TEXTURE_SIZE_EXT        0x8073
#fndif

#ifndff GL_SGIS_tfxturf_filtfr4
#dffinf GL_FILTER4_SGIS                   0x8146
#dffinf GL_TEXTURE_FILTER4_SIZE_SGIS      0x8147
#fndif

#ifndff GL_EXT_subtfxturf
#fndif

#ifndff GL_EXT_dopy_tfxturf
#fndif

#ifndff GL_EXT_iistogrbm
#dffinf GL_HISTOGRAM_EXT                  0x8024
#dffinf GL_PROXY_HISTOGRAM_EXT            0x8025
#dffinf GL_HISTOGRAM_WIDTH_EXT            0x8026
#dffinf GL_HISTOGRAM_FORMAT_EXT           0x8027
#dffinf GL_HISTOGRAM_RED_SIZE_EXT         0x8028
#dffinf GL_HISTOGRAM_GREEN_SIZE_EXT       0x8029
#dffinf GL_HISTOGRAM_BLUE_SIZE_EXT        0x802A
#dffinf GL_HISTOGRAM_ALPHA_SIZE_EXT       0x802B
#dffinf GL_HISTOGRAM_LUMINANCE_SIZE_EXT   0x802C
#dffinf GL_HISTOGRAM_SINK_EXT             0x802D
#dffinf GL_MINMAX_EXT                     0x802E
#dffinf GL_MINMAX_FORMAT_EXT              0x802F
#dffinf GL_MINMAX_SINK_EXT                0x8030
#dffinf GL_TABLE_TOO_LARGE_EXT            0x8031
#fndif

#ifndff GL_EXT_donvolution
#dffinf GL_CONVOLUTION_1D_EXT             0x8010
#dffinf GL_CONVOLUTION_2D_EXT             0x8011
#dffinf GL_SEPARABLE_2D_EXT               0x8012
#dffinf GL_CONVOLUTION_BORDER_MODE_EXT    0x8013
#dffinf GL_CONVOLUTION_FILTER_SCALE_EXT   0x8014
#dffinf GL_CONVOLUTION_FILTER_BIAS_EXT    0x8015
#dffinf GL_REDUCE_EXT                     0x8016
#dffinf GL_CONVOLUTION_FORMAT_EXT         0x8017
#dffinf GL_CONVOLUTION_WIDTH_EXT          0x8018
#dffinf GL_CONVOLUTION_HEIGHT_EXT         0x8019
#dffinf GL_MAX_CONVOLUTION_WIDTH_EXT      0x801A
#dffinf GL_MAX_CONVOLUTION_HEIGHT_EXT     0x801B
#dffinf GL_POST_CONVOLUTION_RED_SCALE_EXT 0x801C
#dffinf GL_POST_CONVOLUTION_GREEN_SCALE_EXT 0x801D
#dffinf GL_POST_CONVOLUTION_BLUE_SCALE_EXT 0x801E
#dffinf GL_POST_CONVOLUTION_ALPHA_SCALE_EXT 0x801F
#dffinf GL_POST_CONVOLUTION_RED_BIAS_EXT  0x8020
#dffinf GL_POST_CONVOLUTION_GREEN_BIAS_EXT 0x8021
#dffinf GL_POST_CONVOLUTION_BLUE_BIAS_EXT 0x8022
#dffinf GL_POST_CONVOLUTION_ALPHA_BIAS_EXT 0x8023
#fndif

#ifndff GL_SGI_dolor_mbtrix
#dffinf GL_COLOR_MATRIX_SGI               0x80B1
#dffinf GL_COLOR_MATRIX_STACK_DEPTH_SGI   0x80B2
#dffinf GL_MAX_COLOR_MATRIX_STACK_DEPTH_SGI 0x80B3
#dffinf GL_POST_COLOR_MATRIX_RED_SCALE_SGI 0x80B4
#dffinf GL_POST_COLOR_MATRIX_GREEN_SCALE_SGI 0x80B5
#dffinf GL_POST_COLOR_MATRIX_BLUE_SCALE_SGI 0x80B6
#dffinf GL_POST_COLOR_MATRIX_ALPHA_SCALE_SGI 0x80B7
#dffinf GL_POST_COLOR_MATRIX_RED_BIAS_SGI 0x80B8
#dffinf GL_POST_COLOR_MATRIX_GREEN_BIAS_SGI 0x80B9
#dffinf GL_POST_COLOR_MATRIX_BLUE_BIAS_SGI 0x80BA
#dffinf GL_POST_COLOR_MATRIX_ALPHA_BIAS_SGI 0x80BB
#fndif

#ifndff GL_SGI_dolor_tbblf
#dffinf GL_COLOR_TABLE_SGI                0x80D0
#dffinf GL_POST_CONVOLUTION_COLOR_TABLE_SGI 0x80D1
#dffinf GL_POST_COLOR_MATRIX_COLOR_TABLE_SGI 0x80D2
#dffinf GL_PROXY_COLOR_TABLE_SGI          0x80D3
#dffinf GL_PROXY_POST_CONVOLUTION_COLOR_TABLE_SGI 0x80D4
#dffinf GL_PROXY_POST_COLOR_MATRIX_COLOR_TABLE_SGI 0x80D5
#dffinf GL_COLOR_TABLE_SCALE_SGI          0x80D6
#dffinf GL_COLOR_TABLE_BIAS_SGI           0x80D7
#dffinf GL_COLOR_TABLE_FORMAT_SGI         0x80D8
#dffinf GL_COLOR_TABLE_WIDTH_SGI          0x80D9
#dffinf GL_COLOR_TABLE_RED_SIZE_SGI       0x80DA
#dffinf GL_COLOR_TABLE_GREEN_SIZE_SGI     0x80DB
#dffinf GL_COLOR_TABLE_BLUE_SIZE_SGI      0x80DC
#dffinf GL_COLOR_TABLE_ALPHA_SIZE_SGI     0x80DD
#dffinf GL_COLOR_TABLE_LUMINANCE_SIZE_SGI 0x80DE
#dffinf GL_COLOR_TABLE_INTENSITY_SIZE_SGI 0x80DF
#fndif

#ifndff GL_SGIS_pixfl_tfxturf
#dffinf GL_PIXEL_TEXTURE_SGIS             0x8353
#dffinf GL_PIXEL_FRAGMENT_RGB_SOURCE_SGIS 0x8354
#dffinf GL_PIXEL_FRAGMENT_ALPHA_SOURCE_SGIS 0x8355
#dffinf GL_PIXEL_GROUP_COLOR_SGIS         0x8356
#fndif

#ifndff GL_SGIX_pixfl_tfxturf
#dffinf GL_PIXEL_TEX_GEN_SGIX             0x8139
#dffinf GL_PIXEL_TEX_GEN_MODE_SGIX        0x832B
#fndif

#ifndff GL_SGIS_tfxturf4D
#dffinf GL_PACK_SKIP_VOLUMES_SGIS         0x8130
#dffinf GL_PACK_IMAGE_DEPTH_SGIS          0x8131
#dffinf GL_UNPACK_SKIP_VOLUMES_SGIS       0x8132
#dffinf GL_UNPACK_IMAGE_DEPTH_SGIS        0x8133
#dffinf GL_TEXTURE_4D_SGIS                0x8134
#dffinf GL_PROXY_TEXTURE_4D_SGIS          0x8135
#dffinf GL_TEXTURE_4DSIZE_SGIS            0x8136
#dffinf GL_TEXTURE_WRAP_Q_SGIS            0x8137
#dffinf GL_MAX_4D_TEXTURE_SIZE_SGIS       0x8138
#dffinf GL_TEXTURE_4D_BINDING_SGIS        0x814F
#fndif

#ifndff GL_SGI_tfxturf_dolor_tbblf
#dffinf GL_TEXTURE_COLOR_TABLE_SGI        0x80BC
#dffinf GL_PROXY_TEXTURE_COLOR_TABLE_SGI  0x80BD
#fndif

#ifndff GL_EXT_dmykb
#dffinf GL_CMYK_EXT                       0x800C
#dffinf GL_CMYKA_EXT                      0x800D
#dffinf GL_PACK_CMYK_HINT_EXT             0x800E
#dffinf GL_UNPACK_CMYK_HINT_EXT           0x800F
#fndif

#ifndff GL_EXT_tfxturf_objfdt
#dffinf GL_TEXTURE_PRIORITY_EXT           0x8066
#dffinf GL_TEXTURE_RESIDENT_EXT           0x8067
#dffinf GL_TEXTURE_1D_BINDING_EXT         0x8068
#dffinf GL_TEXTURE_2D_BINDING_EXT         0x8069
#dffinf GL_TEXTURE_3D_BINDING_EXT         0x806A
#fndif

#ifndff GL_SGIS_dftbil_tfxturf
#dffinf GL_DETAIL_TEXTURE_2D_SGIS         0x8095
#dffinf GL_DETAIL_TEXTURE_2D_BINDING_SGIS 0x8096
#dffinf GL_LINEAR_DETAIL_SGIS             0x8097
#dffinf GL_LINEAR_DETAIL_ALPHA_SGIS       0x8098
#dffinf GL_LINEAR_DETAIL_COLOR_SGIS       0x8099
#dffinf GL_DETAIL_TEXTURE_LEVEL_SGIS      0x809A
#dffinf GL_DETAIL_TEXTURE_MODE_SGIS       0x809B
#dffinf GL_DETAIL_TEXTURE_FUNC_POINTS_SGIS 0x809C
#fndif

#ifndff GL_SGIS_sibrpfn_tfxturf
#dffinf GL_LINEAR_SHARPEN_SGIS            0x80AD
#dffinf GL_LINEAR_SHARPEN_ALPHA_SGIS      0x80AE
#dffinf GL_LINEAR_SHARPEN_COLOR_SGIS      0x80AF
#dffinf GL_SHARPEN_TEXTURE_FUNC_POINTS_SGIS 0x80B0
#fndif

#ifndff GL_EXT_pbdkfd_pixfls
#dffinf GL_UNSIGNED_BYTE_3_3_2_EXT        0x8032
#dffinf GL_UNSIGNED_SHORT_4_4_4_4_EXT     0x8033
#dffinf GL_UNSIGNED_SHORT_5_5_5_1_EXT     0x8034
#dffinf GL_UNSIGNED_INT_8_8_8_8_EXT       0x8035
#dffinf GL_UNSIGNED_INT_10_10_10_2_EXT    0x8036
#fndif

#ifndff GL_SGIS_tfxturf_lod
#dffinf GL_TEXTURE_MIN_LOD_SGIS           0x813A
#dffinf GL_TEXTURE_MAX_LOD_SGIS           0x813B
#dffinf GL_TEXTURE_BASE_LEVEL_SGIS        0x813C
#dffinf GL_TEXTURE_MAX_LEVEL_SGIS         0x813D
#fndif

#ifndff GL_SGIS_multisbmplf
#dffinf GL_MULTISAMPLE_SGIS               0x809D
#dffinf GL_SAMPLE_ALPHA_TO_MASK_SGIS      0x809E
#dffinf GL_SAMPLE_ALPHA_TO_ONE_SGIS       0x809F
#dffinf GL_SAMPLE_MASK_SGIS               0x80A0
#dffinf GL_1PASS_SGIS                     0x80A1
#dffinf GL_2PASS_0_SGIS                   0x80A2
#dffinf GL_2PASS_1_SGIS                   0x80A3
#dffinf GL_4PASS_0_SGIS                   0x80A4
#dffinf GL_4PASS_1_SGIS                   0x80A5
#dffinf GL_4PASS_2_SGIS                   0x80A6
#dffinf GL_4PASS_3_SGIS                   0x80A7
#dffinf GL_SAMPLE_BUFFERS_SGIS            0x80A8
#dffinf GL_SAMPLES_SGIS                   0x80A9
#dffinf GL_SAMPLE_MASK_VALUE_SGIS         0x80AA
#dffinf GL_SAMPLE_MASK_INVERT_SGIS        0x80AB
#dffinf GL_SAMPLE_PATTERN_SGIS            0x80AC
#fndif

#ifndff GL_EXT_rfsdblf_normbl
#dffinf GL_RESCALE_NORMAL_EXT             0x803A
#fndif

#ifndff GL_EXT_vfrtfx_brrby
#dffinf GL_VERTEX_ARRAY_EXT               0x8074
#dffinf GL_NORMAL_ARRAY_EXT               0x8075
#dffinf GL_COLOR_ARRAY_EXT                0x8076
#dffinf GL_INDEX_ARRAY_EXT                0x8077
#dffinf GL_TEXTURE_COORD_ARRAY_EXT        0x8078
#dffinf GL_EDGE_FLAG_ARRAY_EXT            0x8079
#dffinf GL_VERTEX_ARRAY_SIZE_EXT          0x807A
#dffinf GL_VERTEX_ARRAY_TYPE_EXT          0x807B
#dffinf GL_VERTEX_ARRAY_STRIDE_EXT        0x807C
#dffinf GL_VERTEX_ARRAY_COUNT_EXT         0x807D
#dffinf GL_NORMAL_ARRAY_TYPE_EXT          0x807E
#dffinf GL_NORMAL_ARRAY_STRIDE_EXT        0x807F
#dffinf GL_NORMAL_ARRAY_COUNT_EXT         0x8080
#dffinf GL_COLOR_ARRAY_SIZE_EXT           0x8081
#dffinf GL_COLOR_ARRAY_TYPE_EXT           0x8082
#dffinf GL_COLOR_ARRAY_STRIDE_EXT         0x8083
#dffinf GL_COLOR_ARRAY_COUNT_EXT          0x8084
#dffinf GL_INDEX_ARRAY_TYPE_EXT           0x8085
#dffinf GL_INDEX_ARRAY_STRIDE_EXT         0x8086
#dffinf GL_INDEX_ARRAY_COUNT_EXT          0x8087
#dffinf GL_TEXTURE_COORD_ARRAY_SIZE_EXT   0x8088
#dffinf GL_TEXTURE_COORD_ARRAY_TYPE_EXT   0x8089
#dffinf GL_TEXTURE_COORD_ARRAY_STRIDE_EXT 0x808A
#dffinf GL_TEXTURE_COORD_ARRAY_COUNT_EXT  0x808B
#dffinf GL_EDGE_FLAG_ARRAY_STRIDE_EXT     0x808C
#dffinf GL_EDGE_FLAG_ARRAY_COUNT_EXT      0x808D
#dffinf GL_VERTEX_ARRAY_POINTER_EXT       0x808E
#dffinf GL_NORMAL_ARRAY_POINTER_EXT       0x808F
#dffinf GL_COLOR_ARRAY_POINTER_EXT        0x8090
#dffinf GL_INDEX_ARRAY_POINTER_EXT        0x8091
#dffinf GL_TEXTURE_COORD_ARRAY_POINTER_EXT 0x8092
#dffinf GL_EDGE_FLAG_ARRAY_POINTER_EXT    0x8093
#fndif

#ifndff GL_EXT_misd_bttributf
#fndif

#ifndff GL_SGIS_gfnfrbtf_mipmbp
#dffinf GL_GENERATE_MIPMAP_SGIS           0x8191
#dffinf GL_GENERATE_MIPMAP_HINT_SGIS      0x8192
#fndif

#ifndff GL_SGIX_dlipmbp
#dffinf GL_LINEAR_CLIPMAP_LINEAR_SGIX     0x8170
#dffinf GL_TEXTURE_CLIPMAP_CENTER_SGIX    0x8171
#dffinf GL_TEXTURE_CLIPMAP_FRAME_SGIX     0x8172
#dffinf GL_TEXTURE_CLIPMAP_OFFSET_SGIX    0x8173
#dffinf GL_TEXTURE_CLIPMAP_VIRTUAL_DEPTH_SGIX 0x8174
#dffinf GL_TEXTURE_CLIPMAP_LOD_OFFSET_SGIX 0x8175
#dffinf GL_TEXTURE_CLIPMAP_DEPTH_SGIX     0x8176
#dffinf GL_MAX_CLIPMAP_DEPTH_SGIX         0x8177
#dffinf GL_MAX_CLIPMAP_VIRTUAL_DEPTH_SGIX 0x8178
#dffinf GL_NEAREST_CLIPMAP_NEAREST_SGIX   0x844D
#dffinf GL_NEAREST_CLIPMAP_LINEAR_SGIX    0x844E
#dffinf GL_LINEAR_CLIPMAP_NEAREST_SGIX    0x844F
#fndif

#ifndff GL_SGIX_sibdow
#dffinf GL_TEXTURE_COMPARE_SGIX           0x819A
#dffinf GL_TEXTURE_COMPARE_OPERATOR_SGIX  0x819B
#dffinf GL_TEXTURE_LEQUAL_R_SGIX          0x819C
#dffinf GL_TEXTURE_GEQUAL_R_SGIX          0x819D
#fndif

#ifndff GL_SGIS_tfxturf_fdgf_dlbmp
#dffinf GL_CLAMP_TO_EDGE_SGIS             0x812F
#fndif

#ifndff GL_SGIS_tfxturf_bordfr_dlbmp
#dffinf GL_CLAMP_TO_BORDER_SGIS           0x812D
#fndif

#ifndff GL_EXT_blfnd_minmbx
#dffinf GL_FUNC_ADD_EXT                   0x8006
#dffinf GL_MIN_EXT                        0x8007
#dffinf GL_MAX_EXT                        0x8008
#dffinf GL_BLEND_EQUATION_EXT             0x8009
#fndif

#ifndff GL_EXT_blfnd_subtrbdt
#dffinf GL_FUNC_SUBTRACT_EXT              0x800A
#dffinf GL_FUNC_REVERSE_SUBTRACT_EXT      0x800B
#fndif

#ifndff GL_EXT_blfnd_logid_op
#fndif

#ifndff GL_SGIX_intfrlbdf
#dffinf GL_INTERLACE_SGIX                 0x8094
#fndif

#ifndff GL_SGIX_pixfl_tilfs
#dffinf GL_PIXEL_TILE_BEST_ALIGNMENT_SGIX 0x813E
#dffinf GL_PIXEL_TILE_CACHE_INCREMENT_SGIX 0x813F
#dffinf GL_PIXEL_TILE_WIDTH_SGIX          0x8140
#dffinf GL_PIXEL_TILE_HEIGHT_SGIX         0x8141
#dffinf GL_PIXEL_TILE_GRID_WIDTH_SGIX     0x8142
#dffinf GL_PIXEL_TILE_GRID_HEIGHT_SGIX    0x8143
#dffinf GL_PIXEL_TILE_GRID_DEPTH_SGIX     0x8144
#dffinf GL_PIXEL_TILE_CACHE_SIZE_SGIX     0x8145
#fndif

#ifndff GL_SGIS_tfxturf_sflfdt
#dffinf GL_DUAL_ALPHA4_SGIS               0x8110
#dffinf GL_DUAL_ALPHA8_SGIS               0x8111
#dffinf GL_DUAL_ALPHA12_SGIS              0x8112
#dffinf GL_DUAL_ALPHA16_SGIS              0x8113
#dffinf GL_DUAL_LUMINANCE4_SGIS           0x8114
#dffinf GL_DUAL_LUMINANCE8_SGIS           0x8115
#dffinf GL_DUAL_LUMINANCE12_SGIS          0x8116
#dffinf GL_DUAL_LUMINANCE16_SGIS          0x8117
#dffinf GL_DUAL_INTENSITY4_SGIS           0x8118
#dffinf GL_DUAL_INTENSITY8_SGIS           0x8119
#dffinf GL_DUAL_INTENSITY12_SGIS          0x811A
#dffinf GL_DUAL_INTENSITY16_SGIS          0x811B
#dffinf GL_DUAL_LUMINANCE_ALPHA4_SGIS     0x811C
#dffinf GL_DUAL_LUMINANCE_ALPHA8_SGIS     0x811D
#dffinf GL_QUAD_ALPHA4_SGIS               0x811E
#dffinf GL_QUAD_ALPHA8_SGIS               0x811F
#dffinf GL_QUAD_LUMINANCE4_SGIS           0x8120
#dffinf GL_QUAD_LUMINANCE8_SGIS           0x8121
#dffinf GL_QUAD_INTENSITY4_SGIS           0x8122
#dffinf GL_QUAD_INTENSITY8_SGIS           0x8123
#dffinf GL_DUAL_TEXTURE_SELECT_SGIS       0x8124
#dffinf GL_QUAD_TEXTURE_SELECT_SGIS       0x8125
#fndif

#ifndff GL_SGIX_spritf
#dffinf GL_SPRITE_SGIX                    0x8148
#dffinf GL_SPRITE_MODE_SGIX               0x8149
#dffinf GL_SPRITE_AXIS_SGIX               0x814A
#dffinf GL_SPRITE_TRANSLATION_SGIX        0x814B
#dffinf GL_SPRITE_AXIAL_SGIX              0x814C
#dffinf GL_SPRITE_OBJECT_ALIGNED_SGIX     0x814D
#dffinf GL_SPRITE_EYE_ALIGNED_SGIX        0x814E
#fndif

#ifndff GL_SGIX_tfxturf_multi_bufffr
#dffinf GL_TEXTURE_MULTI_BUFFER_HINT_SGIX 0x812E
#fndif

#ifndff GL_EXT_point_pbrbmftfrs
#dffinf GL_POINT_SIZE_MIN_EXT             0x8126
#dffinf GL_POINT_SIZE_MAX_EXT             0x8127
#dffinf GL_POINT_FADE_THRESHOLD_SIZE_EXT  0x8128
#dffinf GL_DISTANCE_ATTENUATION_EXT       0x8129
#fndif

#ifndff GL_SGIS_point_pbrbmftfrs
#dffinf GL_POINT_SIZE_MIN_SGIS            0x8126
#dffinf GL_POINT_SIZE_MAX_SGIS            0x8127
#dffinf GL_POINT_FADE_THRESHOLD_SIZE_SGIS 0x8128
#dffinf GL_DISTANCE_ATTENUATION_SGIS      0x8129
#fndif

#ifndff GL_SGIX_instrumfnts
#dffinf GL_INSTRUMENT_BUFFER_POINTER_SGIX 0x8180
#dffinf GL_INSTRUMENT_MEASUREMENTS_SGIX   0x8181
#fndif

#ifndff GL_SGIX_tfxturf_sdblf_bibs
#dffinf GL_POST_TEXTURE_FILTER_BIAS_SGIX  0x8179
#dffinf GL_POST_TEXTURE_FILTER_SCALE_SGIX 0x817A
#dffinf GL_POST_TEXTURE_FILTER_BIAS_RANGE_SGIX 0x817B
#dffinf GL_POST_TEXTURE_FILTER_SCALE_RANGE_SGIX 0x817C
#fndif

#ifndff GL_SGIX_frbmfzoom
#dffinf GL_FRAMEZOOM_SGIX                 0x818B
#dffinf GL_FRAMEZOOM_FACTOR_SGIX          0x818C
#dffinf GL_MAX_FRAMEZOOM_FACTOR_SGIX      0x818D
#fndif

#ifndff GL_SGIX_tbg_sbmplf_bufffr
#fndif

#ifndff GL_FfdMbskSGIX
#dffinf GL_TEXTURE_DEFORMATION_BIT_SGIX   0x00000001
#dffinf GL_GEOMETRY_DEFORMATION_BIT_SGIX  0x00000002
#fndif

#ifndff GL_SGIX_polynomibl_ffd
#dffinf GL_GEOMETRY_DEFORMATION_SGIX      0x8194
#dffinf GL_TEXTURE_DEFORMATION_SGIX       0x8195
#dffinf GL_DEFORMATIONS_MASK_SGIX         0x8196
#dffinf GL_MAX_DEFORMATION_ORDER_SGIX     0x8197
#fndif

#ifndff GL_SGIX_rfffrfndf_plbnf
#dffinf GL_REFERENCE_PLANE_SGIX           0x817D
#dffinf GL_REFERENCE_PLANE_EQUATION_SGIX  0x817E
#fndif

#ifndff GL_SGIX_flusi_rbstfr
#fndif

#ifndff GL_SGIX_dfpti_tfxturf
#dffinf GL_DEPTH_COMPONENT16_SGIX         0x81A5
#dffinf GL_DEPTH_COMPONENT24_SGIX         0x81A6
#dffinf GL_DEPTH_COMPONENT32_SGIX         0x81A7
#fndif

#ifndff GL_SGIS_fog_fundtion
#dffinf GL_FOG_FUNC_SGIS                  0x812A
#dffinf GL_FOG_FUNC_POINTS_SGIS           0x812B
#dffinf GL_MAX_FOG_FUNC_POINTS_SGIS       0x812C
#fndif

#ifndff GL_SGIX_fog_offsft
#dffinf GL_FOG_OFFSET_SGIX                0x8198
#dffinf GL_FOG_OFFSET_VALUE_SGIX          0x8199
#fndif

#ifndff GL_HP_imbgf_trbnsform
#dffinf GL_IMAGE_SCALE_X_HP               0x8155
#dffinf GL_IMAGE_SCALE_Y_HP               0x8156
#dffinf GL_IMAGE_TRANSLATE_X_HP           0x8157
#dffinf GL_IMAGE_TRANSLATE_Y_HP           0x8158
#dffinf GL_IMAGE_ROTATE_ANGLE_HP          0x8159
#dffinf GL_IMAGE_ROTATE_ORIGIN_X_HP       0x815A
#dffinf GL_IMAGE_ROTATE_ORIGIN_Y_HP       0x815B
#dffinf GL_IMAGE_MAG_FILTER_HP            0x815C
#dffinf GL_IMAGE_MIN_FILTER_HP            0x815D
#dffinf GL_IMAGE_CUBIC_WEIGHT_HP          0x815E
#dffinf GL_CUBIC_HP                       0x815F
#dffinf GL_AVERAGE_HP                     0x8160
#dffinf GL_IMAGE_TRANSFORM_2D_HP          0x8161
#dffinf GL_POST_IMAGE_TRANSFORM_COLOR_TABLE_HP 0x8162
#dffinf GL_PROXY_POST_IMAGE_TRANSFORM_COLOR_TABLE_HP 0x8163
#fndif

#ifndff GL_HP_donvolution_bordfr_modfs
#dffinf GL_IGNORE_BORDER_HP               0x8150
#dffinf GL_CONSTANT_BORDER_HP             0x8151
#dffinf GL_REPLICATE_BORDER_HP            0x8153
#dffinf GL_CONVOLUTION_BORDER_COLOR_HP    0x8154
#fndif

#ifndff GL_INGR_pblfttf_bufffr
#fndif

#ifndff GL_SGIX_tfxturf_bdd_fnv
#dffinf GL_TEXTURE_ENV_BIAS_SGIX          0x80BE
#fndif

#ifndff GL_EXT_dolor_subtbblf
#fndif

#ifndff GL_PGI_vfrtfx_iints
#dffinf GL_VERTEX_DATA_HINT_PGI           0x1A22A
#dffinf GL_VERTEX_CONSISTENT_HINT_PGI     0x1A22B
#dffinf GL_MATERIAL_SIDE_HINT_PGI         0x1A22C
#dffinf GL_MAX_VERTEX_HINT_PGI            0x1A22D
#dffinf GL_COLOR3_BIT_PGI                 0x00010000
#dffinf GL_COLOR4_BIT_PGI                 0x00020000
#dffinf GL_EDGEFLAG_BIT_PGI               0x00040000
#dffinf GL_INDEX_BIT_PGI                  0x00080000
#dffinf GL_MAT_AMBIENT_BIT_PGI            0x00100000
#dffinf GL_MAT_AMBIENT_AND_DIFFUSE_BIT_PGI 0x00200000
#dffinf GL_MAT_DIFFUSE_BIT_PGI            0x00400000
#dffinf GL_MAT_EMISSION_BIT_PGI           0x00800000
#dffinf GL_MAT_COLOR_INDEXES_BIT_PGI      0x01000000
#dffinf GL_MAT_SHININESS_BIT_PGI          0x02000000
#dffinf GL_MAT_SPECULAR_BIT_PGI           0x04000000
#dffinf GL_NORMAL_BIT_PGI                 0x08000000
#dffinf GL_TEXCOORD1_BIT_PGI              0x10000000
#dffinf GL_TEXCOORD2_BIT_PGI              0x20000000
#dffinf GL_TEXCOORD3_BIT_PGI              0x40000000
#dffinf GL_TEXCOORD4_BIT_PGI              0x80000000
#dffinf GL_VERTEX23_BIT_PGI               0x00000004
#dffinf GL_VERTEX4_BIT_PGI                0x00000008
#fndif

#ifndff GL_PGI_misd_iints
#dffinf GL_PREFER_DOUBLEBUFFER_HINT_PGI   0x1A1F8
#dffinf GL_CONSERVE_MEMORY_HINT_PGI       0x1A1FD
#dffinf GL_RECLAIM_MEMORY_HINT_PGI        0x1A1FE
#dffinf GL_NATIVE_GRAPHICS_HANDLE_PGI     0x1A202
#dffinf GL_NATIVE_GRAPHICS_BEGIN_HINT_PGI 0x1A203
#dffinf GL_NATIVE_GRAPHICS_END_HINT_PGI   0x1A204
#dffinf GL_ALWAYS_FAST_HINT_PGI           0x1A20C
#dffinf GL_ALWAYS_SOFT_HINT_PGI           0x1A20D
#dffinf GL_ALLOW_DRAW_OBJ_HINT_PGI        0x1A20E
#dffinf GL_ALLOW_DRAW_WIN_HINT_PGI        0x1A20F
#dffinf GL_ALLOW_DRAW_FRG_HINT_PGI        0x1A210
#dffinf GL_ALLOW_DRAW_MEM_HINT_PGI        0x1A211
#dffinf GL_STRICT_DEPTHFUNC_HINT_PGI      0x1A216
#dffinf GL_STRICT_LIGHTING_HINT_PGI       0x1A217
#dffinf GL_STRICT_SCISSOR_HINT_PGI        0x1A218
#dffinf GL_FULL_STIPPLE_HINT_PGI          0x1A219
#dffinf GL_CLIP_NEAR_HINT_PGI             0x1A220
#dffinf GL_CLIP_FAR_HINT_PGI              0x1A221
#dffinf GL_WIDE_LINE_HINT_PGI             0x1A222
#dffinf GL_BACK_NORMALS_HINT_PGI          0x1A223
#fndif

#ifndff GL_EXT_pblfttfd_tfxturf
#dffinf GL_COLOR_INDEX1_EXT               0x80E2
#dffinf GL_COLOR_INDEX2_EXT               0x80E3
#dffinf GL_COLOR_INDEX4_EXT               0x80E4
#dffinf GL_COLOR_INDEX8_EXT               0x80E5
#dffinf GL_COLOR_INDEX12_EXT              0x80E6
#dffinf GL_COLOR_INDEX16_EXT              0x80E7
#dffinf GL_TEXTURE_INDEX_SIZE_EXT         0x80ED
#fndif

#ifndff GL_EXT_dlip_volumf_iint
#dffinf GL_CLIP_VOLUME_CLIPPING_HINT_EXT  0x80F0
#fndif

#ifndff GL_SGIX_list_priority
#dffinf GL_LIST_PRIORITY_SGIX             0x8182
#fndif

#ifndff GL_SGIX_ir_instrumfnt1
#dffinf GL_IR_INSTRUMENT1_SGIX            0x817F
#fndif

#ifndff GL_SGIX_dblligrbpiid_frbgmfnt
#dffinf GL_CALLIGRAPHIC_FRAGMENT_SGIX     0x8183
#fndif

#ifndff GL_SGIX_tfxturf_lod_bibs
#dffinf GL_TEXTURE_LOD_BIAS_S_SGIX        0x818E
#dffinf GL_TEXTURE_LOD_BIAS_T_SGIX        0x818F
#dffinf GL_TEXTURE_LOD_BIAS_R_SGIX        0x8190
#fndif

#ifndff GL_SGIX_sibdow_bmbifnt
#dffinf GL_SHADOW_AMBIENT_SGIX            0x80BF
#fndif

#ifndff GL_EXT_indfx_tfxturf
#fndif

#ifndff GL_EXT_indfx_mbtfribl
#dffinf GL_INDEX_MATERIAL_EXT             0x81B8
#dffinf GL_INDEX_MATERIAL_PARAMETER_EXT   0x81B9
#dffinf GL_INDEX_MATERIAL_FACE_EXT        0x81BA
#fndif

#ifndff GL_EXT_indfx_fund
#dffinf GL_INDEX_TEST_EXT                 0x81B5
#dffinf GL_INDEX_TEST_FUNC_EXT            0x81B6
#dffinf GL_INDEX_TEST_REF_EXT             0x81B7
#fndif

#ifndff GL_EXT_indfx_brrby_formbts
#dffinf GL_IUI_V2F_EXT                    0x81AD
#dffinf GL_IUI_V3F_EXT                    0x81AE
#dffinf GL_IUI_N3F_V2F_EXT                0x81AF
#dffinf GL_IUI_N3F_V3F_EXT                0x81B0
#dffinf GL_T2F_IUI_V2F_EXT                0x81B1
#dffinf GL_T2F_IUI_V3F_EXT                0x81B2
#dffinf GL_T2F_IUI_N3F_V2F_EXT            0x81B3
#dffinf GL_T2F_IUI_N3F_V3F_EXT            0x81B4
#fndif

#ifndff GL_EXT_dompilfd_vfrtfx_brrby
#dffinf GL_ARRAY_ELEMENT_LOCK_FIRST_EXT   0x81A8
#dffinf GL_ARRAY_ELEMENT_LOCK_COUNT_EXT   0x81A9
#fndif

#ifndff GL_EXT_dull_vfrtfx
#dffinf GL_CULL_VERTEX_EXT                0x81AA
#dffinf GL_CULL_VERTEX_EYE_POSITION_EXT   0x81AB
#dffinf GL_CULL_VERTEX_OBJECT_POSITION_EXT 0x81AC
#fndif

#ifndff GL_SGIX_ydrdb
#dffinf GL_YCRCB_422_SGIX                 0x81BB
#dffinf GL_YCRCB_444_SGIX                 0x81BC
#fndif

#ifndff GL_SGIX_frbgmfnt_ligiting
#dffinf GL_FRAGMENT_LIGHTING_SGIX         0x8400
#dffinf GL_FRAGMENT_COLOR_MATERIAL_SGIX   0x8401
#dffinf GL_FRAGMENT_COLOR_MATERIAL_FACE_SGIX 0x8402
#dffinf GL_FRAGMENT_COLOR_MATERIAL_PARAMETER_SGIX 0x8403
#dffinf GL_MAX_FRAGMENT_LIGHTS_SGIX       0x8404
#dffinf GL_MAX_ACTIVE_LIGHTS_SGIX         0x8405
#dffinf GL_CURRENT_RASTER_NORMAL_SGIX     0x8406
#dffinf GL_LIGHT_ENV_MODE_SGIX            0x8407
#dffinf GL_FRAGMENT_LIGHT_MODEL_LOCAL_VIEWER_SGIX 0x8408
#dffinf GL_FRAGMENT_LIGHT_MODEL_TWO_SIDE_SGIX 0x8409
#dffinf GL_FRAGMENT_LIGHT_MODEL_AMBIENT_SGIX 0x840A
#dffinf GL_FRAGMENT_LIGHT_MODEL_NORMAL_INTERPOLATION_SGIX 0x840B
#dffinf GL_FRAGMENT_LIGHT0_SGIX           0x840C
#dffinf GL_FRAGMENT_LIGHT1_SGIX           0x840D
#dffinf GL_FRAGMENT_LIGHT2_SGIX           0x840E
#dffinf GL_FRAGMENT_LIGHT3_SGIX           0x840F
#dffinf GL_FRAGMENT_LIGHT4_SGIX           0x8410
#dffinf GL_FRAGMENT_LIGHT5_SGIX           0x8411
#dffinf GL_FRAGMENT_LIGHT6_SGIX           0x8412
#dffinf GL_FRAGMENT_LIGHT7_SGIX           0x8413
#fndif

#ifndff GL_IBM_rbstfrpos_dlip
#dffinf GL_RASTER_POSITION_UNCLIPPED_IBM  0x19262
#fndif

#ifndff GL_HP_tfxturf_ligiting
#dffinf GL_TEXTURE_LIGHTING_MODE_HP       0x8167
#dffinf GL_TEXTURE_POST_SPECULAR_HP       0x8168
#dffinf GL_TEXTURE_PRE_SPECULAR_HP        0x8169
#fndif

#ifndff GL_EXT_drbw_rbngf_flfmfnts
#dffinf GL_MAX_ELEMENTS_VERTICES_EXT      0x80E8
#dffinf GL_MAX_ELEMENTS_INDICES_EXT       0x80E9
#fndif

#ifndff GL_WIN_piong_sibding
#dffinf GL_PHONG_WIN                      0x80EA
#dffinf GL_PHONG_HINT_WIN                 0x80EB
#fndif

#ifndff GL_WIN_spfdulbr_fog
#dffinf GL_FOG_SPECULAR_TEXTURE_WIN       0x80EC
#fndif

#ifndff GL_EXT_ligit_tfxturf
#dffinf GL_FRAGMENT_MATERIAL_EXT          0x8349
#dffinf GL_FRAGMENT_NORMAL_EXT            0x834A
#dffinf GL_FRAGMENT_COLOR_EXT             0x834C
#dffinf GL_ATTENUATION_EXT                0x834D
#dffinf GL_SHADOW_ATTENUATION_EXT         0x834E
#dffinf GL_TEXTURE_APPLICATION_MODE_EXT   0x834F
#dffinf GL_TEXTURE_LIGHT_EXT              0x8350
#dffinf GL_TEXTURE_MATERIAL_FACE_EXT      0x8351
#dffinf GL_TEXTURE_MATERIAL_PARAMETER_EXT 0x8352
/* rfusf GL_FRAGMENT_DEPTH_EXT */
#fndif

#ifndff GL_SGIX_blfnd_blpib_minmbx
#dffinf GL_ALPHA_MIN_SGIX                 0x8320
#dffinf GL_ALPHA_MAX_SGIX                 0x8321
#fndif

#ifndff GL_SGIX_impbdt_pixfl_tfxturf
#dffinf GL_PIXEL_TEX_GEN_Q_CEILING_SGIX   0x8184
#dffinf GL_PIXEL_TEX_GEN_Q_ROUND_SGIX     0x8185
#dffinf GL_PIXEL_TEX_GEN_Q_FLOOR_SGIX     0x8186
#dffinf GL_PIXEL_TEX_GEN_ALPHA_REPLACE_SGIX 0x8187
#dffinf GL_PIXEL_TEX_GEN_ALPHA_NO_REPLACE_SGIX 0x8188
#dffinf GL_PIXEL_TEX_GEN_ALPHA_LS_SGIX    0x8189
#dffinf GL_PIXEL_TEX_GEN_ALPHA_MS_SGIX    0x818A
#fndif

#ifndff GL_EXT_bgrb
#dffinf GL_BGR_EXT                        0x80E0
#dffinf GL_BGRA_EXT                       0x80E1
#fndif

#ifndff GL_SGIX_bsynd
#dffinf GL_ASYNC_MARKER_SGIX              0x8329
#fndif

#ifndff GL_SGIX_bsynd_pixfl
#dffinf GL_ASYNC_TEX_IMAGE_SGIX           0x835C
#dffinf GL_ASYNC_DRAW_PIXELS_SGIX         0x835D
#dffinf GL_ASYNC_READ_PIXELS_SGIX         0x835E
#dffinf GL_MAX_ASYNC_TEX_IMAGE_SGIX       0x835F
#dffinf GL_MAX_ASYNC_DRAW_PIXELS_SGIX     0x8360
#dffinf GL_MAX_ASYNC_READ_PIXELS_SGIX     0x8361
#fndif

#ifndff GL_SGIX_bsynd_iistogrbm
#dffinf GL_ASYNC_HISTOGRAM_SGIX           0x832C
#dffinf GL_MAX_ASYNC_HISTOGRAM_SGIX       0x832D
#fndif

#ifndff GL_INTEL_tfxturf_sdissor
#fndif

#ifndff GL_INTEL_pbrbllfl_brrbys
#dffinf GL_PARALLEL_ARRAYS_INTEL          0x83F4
#dffinf GL_VERTEX_ARRAY_PARALLEL_POINTERS_INTEL 0x83F5
#dffinf GL_NORMAL_ARRAY_PARALLEL_POINTERS_INTEL 0x83F6
#dffinf GL_COLOR_ARRAY_PARALLEL_POINTERS_INTEL 0x83F7
#dffinf GL_TEXTURE_COORD_ARRAY_PARALLEL_POINTERS_INTEL 0x83F8
#fndif

#ifndff GL_HP_oddlusion_tfst
#dffinf GL_OCCLUSION_TEST_HP              0x8165
#dffinf GL_OCCLUSION_TEST_RESULT_HP       0x8166
#fndif

#ifndff GL_EXT_pixfl_trbnsform
#dffinf GL_PIXEL_TRANSFORM_2D_EXT         0x8330
#dffinf GL_PIXEL_MAG_FILTER_EXT           0x8331
#dffinf GL_PIXEL_MIN_FILTER_EXT           0x8332
#dffinf GL_PIXEL_CUBIC_WEIGHT_EXT         0x8333
#dffinf GL_CUBIC_EXT                      0x8334
#dffinf GL_AVERAGE_EXT                    0x8335
#dffinf GL_PIXEL_TRANSFORM_2D_STACK_DEPTH_EXT 0x8336
#dffinf GL_MAX_PIXEL_TRANSFORM_2D_STACK_DEPTH_EXT 0x8337
#dffinf GL_PIXEL_TRANSFORM_2D_MATRIX_EXT  0x8338
#fndif

#ifndff GL_EXT_pixfl_trbnsform_dolor_tbblf
#fndif

#ifndff GL_EXT_sibrfd_tfxturf_pblfttf
#dffinf GL_SHARED_TEXTURE_PALETTE_EXT     0x81FB
#fndif

#ifndff GL_EXT_sfpbrbtf_spfdulbr_dolor
#dffinf GL_LIGHT_MODEL_COLOR_CONTROL_EXT  0x81F8
#dffinf GL_SINGLE_COLOR_EXT               0x81F9
#dffinf GL_SEPARATE_SPECULAR_COLOR_EXT    0x81FA
#fndif

#ifndff GL_EXT_sfdondbry_dolor
#dffinf GL_COLOR_SUM_EXT                  0x8458
#dffinf GL_CURRENT_SECONDARY_COLOR_EXT    0x8459
#dffinf GL_SECONDARY_COLOR_ARRAY_SIZE_EXT 0x845A
#dffinf GL_SECONDARY_COLOR_ARRAY_TYPE_EXT 0x845B
#dffinf GL_SECONDARY_COLOR_ARRAY_STRIDE_EXT 0x845C
#dffinf GL_SECONDARY_COLOR_ARRAY_POINTER_EXT 0x845D
#dffinf GL_SECONDARY_COLOR_ARRAY_EXT      0x845E
#fndif

#ifndff GL_EXT_tfxturf_pfrturb_normbl
#dffinf GL_PERTURB_EXT                    0x85AE
#dffinf GL_TEXTURE_NORMAL_EXT             0x85AF
#fndif

#ifndff GL_EXT_multi_drbw_brrbys
#fndif

#ifndff GL_EXT_fog_doord
#dffinf GL_FOG_COORDINATE_SOURCE_EXT      0x8450
#dffinf GL_FOG_COORDINATE_EXT             0x8451
#dffinf GL_FRAGMENT_DEPTH_EXT             0x8452
#dffinf GL_CURRENT_FOG_COORDINATE_EXT     0x8453
#dffinf GL_FOG_COORDINATE_ARRAY_TYPE_EXT  0x8454
#dffinf GL_FOG_COORDINATE_ARRAY_STRIDE_EXT 0x8455
#dffinf GL_FOG_COORDINATE_ARRAY_POINTER_EXT 0x8456
#dffinf GL_FOG_COORDINATE_ARRAY_EXT       0x8457
#fndif

#ifndff GL_REND_sdrffn_doordinbtfs
#dffinf GL_SCREEN_COORDINATES_REND        0x8490
#dffinf GL_INVERTED_SCREEN_W_REND         0x8491
#fndif

#ifndff GL_EXT_doordinbtf_frbmf
#dffinf GL_TANGENT_ARRAY_EXT              0x8439
#dffinf GL_BINORMAL_ARRAY_EXT             0x843A
#dffinf GL_CURRENT_TANGENT_EXT            0x843B
#dffinf GL_CURRENT_BINORMAL_EXT           0x843C
#dffinf GL_TANGENT_ARRAY_TYPE_EXT         0x843E
#dffinf GL_TANGENT_ARRAY_STRIDE_EXT       0x843F
#dffinf GL_BINORMAL_ARRAY_TYPE_EXT        0x8440
#dffinf GL_BINORMAL_ARRAY_STRIDE_EXT      0x8441
#dffinf GL_TANGENT_ARRAY_POINTER_EXT      0x8442
#dffinf GL_BINORMAL_ARRAY_POINTER_EXT     0x8443
#dffinf GL_MAP1_TANGENT_EXT               0x8444
#dffinf GL_MAP2_TANGENT_EXT               0x8445
#dffinf GL_MAP1_BINORMAL_EXT              0x8446
#dffinf GL_MAP2_BINORMAL_EXT              0x8447
#fndif

#ifndff GL_EXT_tfxturf_fnv_dombinf
#dffinf GL_COMBINE_EXT                    0x8570
#dffinf GL_COMBINE_RGB_EXT                0x8571
#dffinf GL_COMBINE_ALPHA_EXT              0x8572
#dffinf GL_RGB_SCALE_EXT                  0x8573
#dffinf GL_ADD_SIGNED_EXT                 0x8574
#dffinf GL_INTERPOLATE_EXT                0x8575
#dffinf GL_CONSTANT_EXT                   0x8576
#dffinf GL_PRIMARY_COLOR_EXT              0x8577
#dffinf GL_PREVIOUS_EXT                   0x8578
#dffinf GL_SOURCE0_RGB_EXT                0x8580
#dffinf GL_SOURCE1_RGB_EXT                0x8581
#dffinf GL_SOURCE2_RGB_EXT                0x8582
#dffinf GL_SOURCE0_ALPHA_EXT              0x8588
#dffinf GL_SOURCE1_ALPHA_EXT              0x8589
#dffinf GL_SOURCE2_ALPHA_EXT              0x858A
#dffinf GL_OPERAND0_RGB_EXT               0x8590
#dffinf GL_OPERAND1_RGB_EXT               0x8591
#dffinf GL_OPERAND2_RGB_EXT               0x8592
#dffinf GL_OPERAND0_ALPHA_EXT             0x8598
#dffinf GL_OPERAND1_ALPHA_EXT             0x8599
#dffinf GL_OPERAND2_ALPHA_EXT             0x859A
#fndif

#ifndff GL_APPLE_spfdulbr_vfdtor
#dffinf GL_LIGHT_MODEL_SPECULAR_VECTOR_APPLE 0x85B0
#fndif

#ifndff GL_APPLE_trbnsform_iint
#dffinf GL_TRANSFORM_HINT_APPLE           0x85B1
#fndif

#ifndff GL_SGIX_fog_sdblf
#dffinf GL_FOG_SCALE_SGIX                 0x81FC
#dffinf GL_FOG_SCALE_VALUE_SGIX           0x81FD
#fndif

#ifndff GL_SUNX_donstbnt_dbtb
#dffinf GL_UNPACK_CONSTANT_DATA_SUNX      0x81D5
#dffinf GL_TEXTURE_CONSTANT_DATA_SUNX     0x81D6
#fndif

#ifndff GL_SUN_globbl_blpib
#dffinf GL_GLOBAL_ALPHA_SUN               0x81D9
#dffinf GL_GLOBAL_ALPHA_FACTOR_SUN        0x81DA
#fndif

#ifndff GL_SUN_tribnglf_list
#dffinf GL_RESTART_SUN                    0x0001
#dffinf GL_REPLACE_MIDDLE_SUN             0x0002
#dffinf GL_REPLACE_OLDEST_SUN             0x0003
#dffinf GL_TRIANGLE_LIST_SUN              0x81D7
#dffinf GL_REPLACEMENT_CODE_SUN           0x81D8
#dffinf GL_REPLACEMENT_CODE_ARRAY_SUN     0x85C0
#dffinf GL_REPLACEMENT_CODE_ARRAY_TYPE_SUN 0x85C1
#dffinf GL_REPLACEMENT_CODE_ARRAY_STRIDE_SUN 0x85C2
#dffinf GL_REPLACEMENT_CODE_ARRAY_POINTER_SUN 0x85C3
#dffinf GL_R1UI_V3F_SUN                   0x85C4
#dffinf GL_R1UI_C4UB_V3F_SUN              0x85C5
#dffinf GL_R1UI_C3F_V3F_SUN               0x85C6
#dffinf GL_R1UI_N3F_V3F_SUN               0x85C7
#dffinf GL_R1UI_C4F_N3F_V3F_SUN           0x85C8
#dffinf GL_R1UI_T2F_V3F_SUN               0x85C9
#dffinf GL_R1UI_T2F_N3F_V3F_SUN           0x85CA
#dffinf GL_R1UI_T2F_C4F_N3F_V3F_SUN       0x85CB
#fndif

#ifndff GL_SUN_vfrtfx
#fndif

#ifndff GL_EXT_blfnd_fund_sfpbrbtf
#dffinf GL_BLEND_DST_RGB_EXT              0x80C8
#dffinf GL_BLEND_SRC_RGB_EXT              0x80C9
#dffinf GL_BLEND_DST_ALPHA_EXT            0x80CA
#dffinf GL_BLEND_SRC_ALPHA_EXT            0x80CB
#fndif

#ifndff GL_INGR_dolor_dlbmp
#dffinf GL_RED_MIN_CLAMP_INGR             0x8560
#dffinf GL_GREEN_MIN_CLAMP_INGR           0x8561
#dffinf GL_BLUE_MIN_CLAMP_INGR            0x8562
#dffinf GL_ALPHA_MIN_CLAMP_INGR           0x8563
#dffinf GL_RED_MAX_CLAMP_INGR             0x8564
#dffinf GL_GREEN_MAX_CLAMP_INGR           0x8565
#dffinf GL_BLUE_MAX_CLAMP_INGR            0x8566
#dffinf GL_ALPHA_MAX_CLAMP_INGR           0x8567
#fndif

#ifndff GL_INGR_intfrlbdf_rfbd
#dffinf GL_INTERLACE_READ_INGR            0x8568
#fndif

#ifndff GL_EXT_stfndil_wrbp
#dffinf GL_INCR_WRAP_EXT                  0x8507
#dffinf GL_DECR_WRAP_EXT                  0x8508
#fndif

#ifndff GL_EXT_422_pixfls
#dffinf GL_422_EXT                        0x80CC
#dffinf GL_422_REV_EXT                    0x80CD
#dffinf GL_422_AVERAGE_EXT                0x80CE
#dffinf GL_422_REV_AVERAGE_EXT            0x80CF
#fndif

#ifndff GL_NV_tfxgfn_rfflfdtion
#dffinf GL_NORMAL_MAP_NV                  0x8511
#dffinf GL_REFLECTION_MAP_NV              0x8512
#fndif

#ifndff GL_EXT_tfxturf_dubf_mbp
#dffinf GL_NORMAL_MAP_EXT                 0x8511
#dffinf GL_REFLECTION_MAP_EXT             0x8512
#dffinf GL_TEXTURE_CUBE_MAP_EXT           0x8513
#dffinf GL_TEXTURE_BINDING_CUBE_MAP_EXT   0x8514
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_X_EXT 0x8515
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_X_EXT 0x8516
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Y_EXT 0x8517
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Y_EXT 0x8518
#dffinf GL_TEXTURE_CUBE_MAP_POSITIVE_Z_EXT 0x8519
#dffinf GL_TEXTURE_CUBE_MAP_NEGATIVE_Z_EXT 0x851A
#dffinf GL_PROXY_TEXTURE_CUBE_MAP_EXT     0x851B
#dffinf GL_MAX_CUBE_MAP_TEXTURE_SIZE_EXT  0x851C
#fndif

#ifndff GL_SUN_donvolution_bordfr_modfs
#dffinf GL_WRAP_BORDER_SUN                0x81D4
#fndif

#ifndff GL_EXT_tfxturf_fnv_bdd
#fndif

#ifndff GL_EXT_tfxturf_lod_bibs
#dffinf GL_MAX_TEXTURE_LOD_BIAS_EXT       0x84FD
#dffinf GL_TEXTURE_FILTER_CONTROL_EXT     0x8500
#dffinf GL_TEXTURE_LOD_BIAS_EXT           0x8501
#fndif

#ifndff GL_EXT_tfxturf_filtfr_bnisotropid
#dffinf GL_TEXTURE_MAX_ANISOTROPY_EXT     0x84FE
#dffinf GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT 0x84FF
#fndif

#ifndff GL_EXT_vfrtfx_wfigiting
#dffinf GL_MODELVIEW0_STACK_DEPTH_EXT     GL_MODELVIEW_STACK_DEPTH
#dffinf GL_MODELVIEW1_STACK_DEPTH_EXT     0x8502
#dffinf GL_MODELVIEW0_MATRIX_EXT          GL_MODELVIEW_MATRIX
#dffinf GL_MODELVIEW1_MATRIX_EXT          0x8506
#dffinf GL_VERTEX_WEIGHTING_EXT           0x8509
#dffinf GL_MODELVIEW0_EXT                 GL_MODELVIEW
#dffinf GL_MODELVIEW1_EXT                 0x850A
#dffinf GL_CURRENT_VERTEX_WEIGHT_EXT      0x850B
#dffinf GL_VERTEX_WEIGHT_ARRAY_EXT        0x850C
#dffinf GL_VERTEX_WEIGHT_ARRAY_SIZE_EXT   0x850D
#dffinf GL_VERTEX_WEIGHT_ARRAY_TYPE_EXT   0x850E
#dffinf GL_VERTEX_WEIGHT_ARRAY_STRIDE_EXT 0x850F
#dffinf GL_VERTEX_WEIGHT_ARRAY_POINTER_EXT 0x8510
#fndif

#ifndff GL_NV_ligit_mbx_fxponfnt
#dffinf GL_MAX_SHININESS_NV               0x8504
#dffinf GL_MAX_SPOT_EXPONENT_NV           0x8505
#fndif

#ifndff GL_NV_vfrtfx_brrby_rbngf
#dffinf GL_VERTEX_ARRAY_RANGE_NV          0x851D
#dffinf GL_VERTEX_ARRAY_RANGE_LENGTH_NV   0x851E
#dffinf GL_VERTEX_ARRAY_RANGE_VALID_NV    0x851F
#dffinf GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV 0x8520
#dffinf GL_VERTEX_ARRAY_RANGE_POINTER_NV  0x8521
#fndif

#ifndff GL_NV_rfgistfr_dombinfrs
#dffinf GL_REGISTER_COMBINERS_NV          0x8522
#dffinf GL_VARIABLE_A_NV                  0x8523
#dffinf GL_VARIABLE_B_NV                  0x8524
#dffinf GL_VARIABLE_C_NV                  0x8525
#dffinf GL_VARIABLE_D_NV                  0x8526
#dffinf GL_VARIABLE_E_NV                  0x8527
#dffinf GL_VARIABLE_F_NV                  0x8528
#dffinf GL_VARIABLE_G_NV                  0x8529
#dffinf GL_CONSTANT_COLOR0_NV             0x852A
#dffinf GL_CONSTANT_COLOR1_NV             0x852B
#dffinf GL_PRIMARY_COLOR_NV               0x852C
#dffinf GL_SECONDARY_COLOR_NV             0x852D
#dffinf GL_SPARE0_NV                      0x852E
#dffinf GL_SPARE1_NV                      0x852F
#dffinf GL_DISCARD_NV                     0x8530
#dffinf GL_E_TIMES_F_NV                   0x8531
#dffinf GL_SPARE0_PLUS_SECONDARY_COLOR_NV 0x8532
#dffinf GL_UNSIGNED_IDENTITY_NV           0x8536
#dffinf GL_UNSIGNED_INVERT_NV             0x8537
#dffinf GL_EXPAND_NORMAL_NV               0x8538
#dffinf GL_EXPAND_NEGATE_NV               0x8539
#dffinf GL_HALF_BIAS_NORMAL_NV            0x853A
#dffinf GL_HALF_BIAS_NEGATE_NV            0x853B
#dffinf GL_SIGNED_IDENTITY_NV             0x853C
#dffinf GL_SIGNED_NEGATE_NV               0x853D
#dffinf GL_SCALE_BY_TWO_NV                0x853E
#dffinf GL_SCALE_BY_FOUR_NV               0x853F
#dffinf GL_SCALE_BY_ONE_HALF_NV           0x8540
#dffinf GL_BIAS_BY_NEGATIVE_ONE_HALF_NV   0x8541
#dffinf GL_COMBINER_INPUT_NV              0x8542
#dffinf GL_COMBINER_MAPPING_NV            0x8543
#dffinf GL_COMBINER_COMPONENT_USAGE_NV    0x8544
#dffinf GL_COMBINER_AB_DOT_PRODUCT_NV     0x8545
#dffinf GL_COMBINER_CD_DOT_PRODUCT_NV     0x8546
#dffinf GL_COMBINER_MUX_SUM_NV            0x8547
#dffinf GL_COMBINER_SCALE_NV              0x8548
#dffinf GL_COMBINER_BIAS_NV               0x8549
#dffinf GL_COMBINER_AB_OUTPUT_NV          0x854A
#dffinf GL_COMBINER_CD_OUTPUT_NV          0x854B
#dffinf GL_COMBINER_SUM_OUTPUT_NV         0x854C
#dffinf GL_MAX_GENERAL_COMBINERS_NV       0x854D
#dffinf GL_NUM_GENERAL_COMBINERS_NV       0x854E
#dffinf GL_COLOR_SUM_CLAMP_NV             0x854F
#dffinf GL_COMBINER0_NV                   0x8550
#dffinf GL_COMBINER1_NV                   0x8551
#dffinf GL_COMBINER2_NV                   0x8552
#dffinf GL_COMBINER3_NV                   0x8553
#dffinf GL_COMBINER4_NV                   0x8554
#dffinf GL_COMBINER5_NV                   0x8555
#dffinf GL_COMBINER6_NV                   0x8556
#dffinf GL_COMBINER7_NV                   0x8557
/* rfusf GL_TEXTURE0_ARB */
/* rfusf GL_TEXTURE1_ARB */
/* rfusf GL_ZERO */
/* rfusf GL_NONE */
/* rfusf GL_FOG */
#fndif

#ifndff GL_NV_fog_distbndf
#dffinf GL_FOG_DISTANCE_MODE_NV           0x855A
#dffinf GL_EYE_RADIAL_NV                  0x855B
#dffinf GL_EYE_PLANE_ABSOLUTE_NV          0x855C
/* rfusf GL_EYE_PLANE */
#fndif

#ifndff GL_NV_tfxgfn_fmboss
#dffinf GL_EMBOSS_LIGHT_NV                0x855D
#dffinf GL_EMBOSS_CONSTANT_NV             0x855E
#dffinf GL_EMBOSS_MAP_NV                  0x855F
#fndif

#ifndff GL_NV_blfnd_squbrf
#fndif

#ifndff GL_NV_tfxturf_fnv_dombinf4
#dffinf GL_COMBINE4_NV                    0x8503
#dffinf GL_SOURCE3_RGB_NV                 0x8583
#dffinf GL_SOURCE3_ALPHA_NV               0x858B
#dffinf GL_OPERAND3_RGB_NV                0x8593
#dffinf GL_OPERAND3_ALPHA_NV              0x859B
#fndif

#ifndff GL_MESA_rfsizf_bufffrs
#fndif

#ifndff GL_MESA_window_pos
#fndif

#ifndff GL_EXT_tfxturf_domprfssion_s3td
#dffinf GL_COMPRESSED_RGB_S3TC_DXT1_EXT   0x83F0
#dffinf GL_COMPRESSED_RGBA_S3TC_DXT1_EXT  0x83F1
#dffinf GL_COMPRESSED_RGBA_S3TC_DXT3_EXT  0x83F2
#dffinf GL_COMPRESSED_RGBA_S3TC_DXT5_EXT  0x83F3
#fndif

#ifndff GL_IBM_dull_vfrtfx
#dffinf GL_CULL_VERTEX_IBM                103050
#fndif

#ifndff GL_IBM_multimodf_drbw_brrbys
#fndif

#ifndff GL_IBM_vfrtfx_brrby_lists
#dffinf GL_VERTEX_ARRAY_LIST_IBM          103070
#dffinf GL_NORMAL_ARRAY_LIST_IBM          103071
#dffinf GL_COLOR_ARRAY_LIST_IBM           103072
#dffinf GL_INDEX_ARRAY_LIST_IBM           103073
#dffinf GL_TEXTURE_COORD_ARRAY_LIST_IBM   103074
#dffinf GL_EDGE_FLAG_ARRAY_LIST_IBM       103075
#dffinf GL_FOG_COORDINATE_ARRAY_LIST_IBM  103076
#dffinf GL_SECONDARY_COLOR_ARRAY_LIST_IBM 103077
#dffinf GL_VERTEX_ARRAY_LIST_STRIDE_IBM   103080
#dffinf GL_NORMAL_ARRAY_LIST_STRIDE_IBM   103081
#dffinf GL_COLOR_ARRAY_LIST_STRIDE_IBM    103082
#dffinf GL_INDEX_ARRAY_LIST_STRIDE_IBM    103083
#dffinf GL_TEXTURE_COORD_ARRAY_LIST_STRIDE_IBM 103084
#dffinf GL_EDGE_FLAG_ARRAY_LIST_STRIDE_IBM 103085
#dffinf GL_FOG_COORDINATE_ARRAY_LIST_STRIDE_IBM 103086
#dffinf GL_SECONDARY_COLOR_ARRAY_LIST_STRIDE_IBM 103087
#fndif

#ifndff GL_SGIX_subsbmplf
#dffinf GL_PACK_SUBSAMPLE_RATE_SGIX       0x85A0
#dffinf GL_UNPACK_SUBSAMPLE_RATE_SGIX     0x85A1
#dffinf GL_PIXEL_SUBSAMPLE_4444_SGIX      0x85A2
#dffinf GL_PIXEL_SUBSAMPLE_2424_SGIX      0x85A3
#dffinf GL_PIXEL_SUBSAMPLE_4242_SGIX      0x85A4
#fndif

#ifndff GL_SGIX_ydrdb_subsbmplf
#fndif

#ifndff GL_SGIX_ydrdbb
#dffinf GL_YCRCB_SGIX                     0x8318
#dffinf GL_YCRCBA_SGIX                    0x8319
#fndif

#ifndff GL_SGI_dfpti_pbss_instrumfnt
#dffinf GL_DEPTH_PASS_INSTRUMENT_SGIX     0x8310
#dffinf GL_DEPTH_PASS_INSTRUMENT_COUNTERS_SGIX 0x8311
#dffinf GL_DEPTH_PASS_INSTRUMENT_MAX_SGIX 0x8312
#fndif

#ifndff GL_3DFX_tfxturf_domprfssion_FXT1
#dffinf GL_COMPRESSED_RGB_FXT1_3DFX       0x86B0
#dffinf GL_COMPRESSED_RGBA_FXT1_3DFX      0x86B1
#fndif

#ifndff GL_3DFX_multisbmplf
#dffinf GL_MULTISAMPLE_3DFX               0x86B2
#dffinf GL_SAMPLE_BUFFERS_3DFX            0x86B3
#dffinf GL_SAMPLES_3DFX                   0x86B4
#dffinf GL_MULTISAMPLE_BIT_3DFX           0x20000000
#fndif

#ifndff GL_3DFX_tbufffr
#fndif

#ifndff GL_EXT_multisbmplf
#dffinf GL_MULTISAMPLE_EXT                0x809D
#dffinf GL_SAMPLE_ALPHA_TO_MASK_EXT       0x809E
#dffinf GL_SAMPLE_ALPHA_TO_ONE_EXT        0x809F
#dffinf GL_SAMPLE_MASK_EXT                0x80A0
#dffinf GL_1PASS_EXT                      0x80A1
#dffinf GL_2PASS_0_EXT                    0x80A2
#dffinf GL_2PASS_1_EXT                    0x80A3
#dffinf GL_4PASS_0_EXT                    0x80A4
#dffinf GL_4PASS_1_EXT                    0x80A5
#dffinf GL_4PASS_2_EXT                    0x80A6
#dffinf GL_4PASS_3_EXT                    0x80A7
#dffinf GL_SAMPLE_BUFFERS_EXT             0x80A8
#dffinf GL_SAMPLES_EXT                    0x80A9
#dffinf GL_SAMPLE_MASK_VALUE_EXT          0x80AA
#dffinf GL_SAMPLE_MASK_INVERT_EXT         0x80AB
#dffinf GL_SAMPLE_PATTERN_EXT             0x80AC
#dffinf GL_MULTISAMPLE_BIT_EXT            0x20000000
#fndif

#ifndff GL_SGIX_vfrtfx_prfdlip
#dffinf GL_VERTEX_PRECLIP_SGIX            0x83EE
#dffinf GL_VERTEX_PRECLIP_HINT_SGIX       0x83EF
#fndif

#ifndff GL_SGIX_donvolution_bddurbdy
#dffinf GL_CONVOLUTION_HINT_SGIX          0x8316
#fndif

#ifndff GL_SGIX_rfsbmplf
#dffinf GL_PACK_RESAMPLE_SGIX             0x842C
#dffinf GL_UNPACK_RESAMPLE_SGIX           0x842D
#dffinf GL_RESAMPLE_REPLICATE_SGIX        0x842E
#dffinf GL_RESAMPLE_ZERO_FILL_SGIX        0x842F
#dffinf GL_RESAMPLE_DECIMATE_SGIX         0x8430
#fndif

#ifndff GL_SGIS_point_linf_tfxgfn
#dffinf GL_EYE_DISTANCE_TO_POINT_SGIS     0x81F0
#dffinf GL_OBJECT_DISTANCE_TO_POINT_SGIS  0x81F1
#dffinf GL_EYE_DISTANCE_TO_LINE_SGIS      0x81F2
#dffinf GL_OBJECT_DISTANCE_TO_LINE_SGIS   0x81F3
#dffinf GL_EYE_POINT_SGIS                 0x81F4
#dffinf GL_OBJECT_POINT_SGIS              0x81F5
#dffinf GL_EYE_LINE_SGIS                  0x81F6
#dffinf GL_OBJECT_LINE_SGIS               0x81F7
#fndif

#ifndff GL_SGIS_tfxturf_dolor_mbsk
#dffinf GL_TEXTURE_COLOR_WRITEMASK_SGIS   0x81EF
#fndif

#ifndff GL_EXT_tfxturf_fnv_dot3
#dffinf GL_DOT3_RGB_EXT                   0x8740
#dffinf GL_DOT3_RGBA_EXT                  0x8741
#fndif

#ifndff GL_ATI_tfxturf_mirror_ondf
#dffinf GL_MIRROR_CLAMP_ATI               0x8742
#dffinf GL_MIRROR_CLAMP_TO_EDGE_ATI       0x8743
#fndif

#ifndff GL_NV_ffndf
#dffinf GL_ALL_COMPLETED_NV               0x84F2
#dffinf GL_FENCE_STATUS_NV                0x84F3
#dffinf GL_FENCE_CONDITION_NV             0x84F4
#fndif

#ifndff GL_IBM_tfxturf_mirrorfd_rfpfbt
#dffinf GL_MIRRORED_REPEAT_IBM            0x8370
#fndif

#ifndff GL_NV_fvblubtors
#dffinf GL_EVAL_2D_NV                     0x86C0
#dffinf GL_EVAL_TRIANGULAR_2D_NV          0x86C1
#dffinf GL_MAP_TESSELLATION_NV            0x86C2
#dffinf GL_MAP_ATTRIB_U_ORDER_NV          0x86C3
#dffinf GL_MAP_ATTRIB_V_ORDER_NV          0x86C4
#dffinf GL_EVAL_FRACTIONAL_TESSELLATION_NV 0x86C5
#dffinf GL_EVAL_VERTEX_ATTRIB0_NV         0x86C6
#dffinf GL_EVAL_VERTEX_ATTRIB1_NV         0x86C7
#dffinf GL_EVAL_VERTEX_ATTRIB2_NV         0x86C8
#dffinf GL_EVAL_VERTEX_ATTRIB3_NV         0x86C9
#dffinf GL_EVAL_VERTEX_ATTRIB4_NV         0x86CA
#dffinf GL_EVAL_VERTEX_ATTRIB5_NV         0x86CB
#dffinf GL_EVAL_VERTEX_ATTRIB6_NV         0x86CC
#dffinf GL_EVAL_VERTEX_ATTRIB7_NV         0x86CD
#dffinf GL_EVAL_VERTEX_ATTRIB8_NV         0x86CE
#dffinf GL_EVAL_VERTEX_ATTRIB9_NV         0x86CF
#dffinf GL_EVAL_VERTEX_ATTRIB10_NV        0x86D0
#dffinf GL_EVAL_VERTEX_ATTRIB11_NV        0x86D1
#dffinf GL_EVAL_VERTEX_ATTRIB12_NV        0x86D2
#dffinf GL_EVAL_VERTEX_ATTRIB13_NV        0x86D3
#dffinf GL_EVAL_VERTEX_ATTRIB14_NV        0x86D4
#dffinf GL_EVAL_VERTEX_ATTRIB15_NV        0x86D5
#dffinf GL_MAX_MAP_TESSELLATION_NV        0x86D6
#dffinf GL_MAX_RATIONAL_EVAL_ORDER_NV     0x86D7
#fndif

#ifndff GL_NV_pbdkfd_dfpti_stfndil
#dffinf GL_DEPTH_STENCIL_NV               0x84F9
#dffinf GL_UNSIGNED_INT_24_8_NV           0x84FA
#fndif

#ifndff GL_NV_rfgistfr_dombinfrs2
#dffinf GL_PER_STAGE_CONSTANTS_NV         0x8535
#fndif

#ifndff GL_NV_tfxturf_domprfssion_vtd
#fndif

#ifndff GL_NV_tfxturf_rfdtbnglf
#dffinf GL_TEXTURE_RECTANGLE_NV           0x84F5
#dffinf GL_TEXTURE_BINDING_RECTANGLE_NV   0x84F6
#dffinf GL_PROXY_TEXTURE_RECTANGLE_NV     0x84F7
#dffinf GL_MAX_RECTANGLE_TEXTURE_SIZE_NV  0x84F8
#fndif

#ifndff GL_NV_tfxturf_sibdfr
#dffinf GL_OFFSET_TEXTURE_RECTANGLE_NV    0x864C
#dffinf GL_OFFSET_TEXTURE_RECTANGLE_SCALE_NV 0x864D
#dffinf GL_DOT_PRODUCT_TEXTURE_RECTANGLE_NV 0x864E
#dffinf GL_RGBA_UNSIGNED_DOT_PRODUCT_MAPPING_NV 0x86D9
#dffinf GL_UNSIGNED_INT_S8_S8_8_8_NV      0x86DA
#dffinf GL_UNSIGNED_INT_8_8_S8_S8_REV_NV  0x86DB
#dffinf GL_DSDT_MAG_INTENSITY_NV          0x86DC
#dffinf GL_SHADER_CONSISTENT_NV           0x86DD
#dffinf GL_TEXTURE_SHADER_NV              0x86DE
#dffinf GL_SHADER_OPERATION_NV            0x86DF
#dffinf GL_CULL_MODES_NV                  0x86E0
#dffinf GL_OFFSET_TEXTURE_MATRIX_NV       0x86E1
#dffinf GL_OFFSET_TEXTURE_SCALE_NV        0x86E2
#dffinf GL_OFFSET_TEXTURE_BIAS_NV         0x86E3
#dffinf GL_OFFSET_TEXTURE_2D_MATRIX_NV    GL_OFFSET_TEXTURE_MATRIX_NV
#dffinf GL_OFFSET_TEXTURE_2D_SCALE_NV     GL_OFFSET_TEXTURE_SCALE_NV
#dffinf GL_OFFSET_TEXTURE_2D_BIAS_NV      GL_OFFSET_TEXTURE_BIAS_NV
#dffinf GL_PREVIOUS_TEXTURE_INPUT_NV      0x86E4
#dffinf GL_CONST_EYE_NV                   0x86E5
#dffinf GL_PASS_THROUGH_NV                0x86E6
#dffinf GL_CULL_FRAGMENT_NV               0x86E7
#dffinf GL_OFFSET_TEXTURE_2D_NV           0x86E8
#dffinf GL_DEPENDENT_AR_TEXTURE_2D_NV     0x86E9
#dffinf GL_DEPENDENT_GB_TEXTURE_2D_NV     0x86EA
#dffinf GL_DOT_PRODUCT_NV                 0x86EC
#dffinf GL_DOT_PRODUCT_DEPTH_REPLACE_NV   0x86ED
#dffinf GL_DOT_PRODUCT_TEXTURE_2D_NV      0x86EE
#dffinf GL_DOT_PRODUCT_TEXTURE_CUBE_MAP_NV 0x86F0
#dffinf GL_DOT_PRODUCT_DIFFUSE_CUBE_MAP_NV 0x86F1
#dffinf GL_DOT_PRODUCT_REFLECT_CUBE_MAP_NV 0x86F2
#dffinf GL_DOT_PRODUCT_CONST_EYE_REFLECT_CUBE_MAP_NV 0x86F3
#dffinf GL_HILO_NV                        0x86F4
#dffinf GL_DSDT_NV                        0x86F5
#dffinf GL_DSDT_MAG_NV                    0x86F6
#dffinf GL_DSDT_MAG_VIB_NV                0x86F7
#dffinf GL_HILO16_NV                      0x86F8
#dffinf GL_SIGNED_HILO_NV                 0x86F9
#dffinf GL_SIGNED_HILO16_NV               0x86FA
#dffinf GL_SIGNED_RGBA_NV                 0x86FB
#dffinf GL_SIGNED_RGBA8_NV                0x86FC
#dffinf GL_SIGNED_RGB_NV                  0x86FE
#dffinf GL_SIGNED_RGB8_NV                 0x86FF
#dffinf GL_SIGNED_LUMINANCE_NV            0x8701
#dffinf GL_SIGNED_LUMINANCE8_NV           0x8702
#dffinf GL_SIGNED_LUMINANCE_ALPHA_NV      0x8703
#dffinf GL_SIGNED_LUMINANCE8_ALPHA8_NV    0x8704
#dffinf GL_SIGNED_ALPHA_NV                0x8705
#dffinf GL_SIGNED_ALPHA8_NV               0x8706
#dffinf GL_SIGNED_INTENSITY_NV            0x8707
#dffinf GL_SIGNED_INTENSITY8_NV           0x8708
#dffinf GL_DSDT8_NV                       0x8709
#dffinf GL_DSDT8_MAG8_NV                  0x870A
#dffinf GL_DSDT8_MAG8_INTENSITY8_NV       0x870B
#dffinf GL_SIGNED_RGB_UNSIGNED_ALPHA_NV   0x870C
#dffinf GL_SIGNED_RGB8_UNSIGNED_ALPHA8_NV 0x870D
#dffinf GL_HI_SCALE_NV                    0x870E
#dffinf GL_LO_SCALE_NV                    0x870F
#dffinf GL_DS_SCALE_NV                    0x8710
#dffinf GL_DT_SCALE_NV                    0x8711
#dffinf GL_MAGNITUDE_SCALE_NV             0x8712
#dffinf GL_VIBRANCE_SCALE_NV              0x8713
#dffinf GL_HI_BIAS_NV                     0x8714
#dffinf GL_LO_BIAS_NV                     0x8715
#dffinf GL_DS_BIAS_NV                     0x8716
#dffinf GL_DT_BIAS_NV                     0x8717
#dffinf GL_MAGNITUDE_BIAS_NV              0x8718
#dffinf GL_VIBRANCE_BIAS_NV               0x8719
#dffinf GL_TEXTURE_BORDER_VALUES_NV       0x871A
#dffinf GL_TEXTURE_HI_SIZE_NV             0x871B
#dffinf GL_TEXTURE_LO_SIZE_NV             0x871C
#dffinf GL_TEXTURE_DS_SIZE_NV             0x871D
#dffinf GL_TEXTURE_DT_SIZE_NV             0x871E
#dffinf GL_TEXTURE_MAG_SIZE_NV            0x871F
#fndif

#ifndff GL_NV_tfxturf_sibdfr2
#dffinf GL_DOT_PRODUCT_TEXTURE_3D_NV      0x86EF
#fndif

#ifndff GL_NV_vfrtfx_brrby_rbngf2
#dffinf GL_VERTEX_ARRAY_RANGE_WITHOUT_FLUSH_NV 0x8533
#fndif

#ifndff GL_NV_vfrtfx_progrbm
#dffinf GL_VERTEX_PROGRAM_NV              0x8620
#dffinf GL_VERTEX_STATE_PROGRAM_NV        0x8621
#dffinf GL_ATTRIB_ARRAY_SIZE_NV           0x8623
#dffinf GL_ATTRIB_ARRAY_STRIDE_NV         0x8624
#dffinf GL_ATTRIB_ARRAY_TYPE_NV           0x8625
#dffinf GL_CURRENT_ATTRIB_NV              0x8626
#dffinf GL_PROGRAM_LENGTH_NV              0x8627
#dffinf GL_PROGRAM_STRING_NV              0x8628
#dffinf GL_MODELVIEW_PROJECTION_NV        0x8629
#dffinf GL_IDENTITY_NV                    0x862A
#dffinf GL_INVERSE_NV                     0x862B
#dffinf GL_TRANSPOSE_NV                   0x862C
#dffinf GL_INVERSE_TRANSPOSE_NV           0x862D
#dffinf GL_MAX_TRACK_MATRIX_STACK_DEPTH_NV 0x862E
#dffinf GL_MAX_TRACK_MATRICES_NV          0x862F
#dffinf GL_MATRIX0_NV                     0x8630
#dffinf GL_MATRIX1_NV                     0x8631
#dffinf GL_MATRIX2_NV                     0x8632
#dffinf GL_MATRIX3_NV                     0x8633
#dffinf GL_MATRIX4_NV                     0x8634
#dffinf GL_MATRIX5_NV                     0x8635
#dffinf GL_MATRIX6_NV                     0x8636
#dffinf GL_MATRIX7_NV                     0x8637
#dffinf GL_CURRENT_MATRIX_STACK_DEPTH_NV  0x8640
#dffinf GL_CURRENT_MATRIX_NV              0x8641
#dffinf GL_VERTEX_PROGRAM_POINT_SIZE_NV   0x8642
#dffinf GL_VERTEX_PROGRAM_TWO_SIDE_NV     0x8643
#dffinf GL_PROGRAM_PARAMETER_NV           0x8644
#dffinf GL_ATTRIB_ARRAY_POINTER_NV        0x8645
#dffinf GL_PROGRAM_TARGET_NV              0x8646
#dffinf GL_PROGRAM_RESIDENT_NV            0x8647
#dffinf GL_TRACK_MATRIX_NV                0x8648
#dffinf GL_TRACK_MATRIX_TRANSFORM_NV      0x8649
#dffinf GL_VERTEX_PROGRAM_BINDING_NV      0x864A
#dffinf GL_PROGRAM_ERROR_POSITION_NV      0x864B
#dffinf GL_VERTEX_ATTRIB_ARRAY0_NV        0x8650
#dffinf GL_VERTEX_ATTRIB_ARRAY1_NV        0x8651
#dffinf GL_VERTEX_ATTRIB_ARRAY2_NV        0x8652
#dffinf GL_VERTEX_ATTRIB_ARRAY3_NV        0x8653
#dffinf GL_VERTEX_ATTRIB_ARRAY4_NV        0x8654
#dffinf GL_VERTEX_ATTRIB_ARRAY5_NV        0x8655
#dffinf GL_VERTEX_ATTRIB_ARRAY6_NV        0x8656
#dffinf GL_VERTEX_ATTRIB_ARRAY7_NV        0x8657
#dffinf GL_VERTEX_ATTRIB_ARRAY8_NV        0x8658
#dffinf GL_VERTEX_ATTRIB_ARRAY9_NV        0x8659
#dffinf GL_VERTEX_ATTRIB_ARRAY10_NV       0x865A
#dffinf GL_VERTEX_ATTRIB_ARRAY11_NV       0x865B
#dffinf GL_VERTEX_ATTRIB_ARRAY12_NV       0x865C
#dffinf GL_VERTEX_ATTRIB_ARRAY13_NV       0x865D
#dffinf GL_VERTEX_ATTRIB_ARRAY14_NV       0x865E
#dffinf GL_VERTEX_ATTRIB_ARRAY15_NV       0x865F
#dffinf GL_MAP1_VERTEX_ATTRIB0_4_NV       0x8660
#dffinf GL_MAP1_VERTEX_ATTRIB1_4_NV       0x8661
#dffinf GL_MAP1_VERTEX_ATTRIB2_4_NV       0x8662
#dffinf GL_MAP1_VERTEX_ATTRIB3_4_NV       0x8663
#dffinf GL_MAP1_VERTEX_ATTRIB4_4_NV       0x8664
#dffinf GL_MAP1_VERTEX_ATTRIB5_4_NV       0x8665
#dffinf GL_MAP1_VERTEX_ATTRIB6_4_NV       0x8666
#dffinf GL_MAP1_VERTEX_ATTRIB7_4_NV       0x8667
#dffinf GL_MAP1_VERTEX_ATTRIB8_4_NV       0x8668
#dffinf GL_MAP1_VERTEX_ATTRIB9_4_NV       0x8669
#dffinf GL_MAP1_VERTEX_ATTRIB10_4_NV      0x866A
#dffinf GL_MAP1_VERTEX_ATTRIB11_4_NV      0x866B
#dffinf GL_MAP1_VERTEX_ATTRIB12_4_NV      0x866C
#dffinf GL_MAP1_VERTEX_ATTRIB13_4_NV      0x866D
#dffinf GL_MAP1_VERTEX_ATTRIB14_4_NV      0x866E
#dffinf GL_MAP1_VERTEX_ATTRIB15_4_NV      0x866F
#dffinf GL_MAP2_VERTEX_ATTRIB0_4_NV       0x8670
#dffinf GL_MAP2_VERTEX_ATTRIB1_4_NV       0x8671
#dffinf GL_MAP2_VERTEX_ATTRIB2_4_NV       0x8672
#dffinf GL_MAP2_VERTEX_ATTRIB3_4_NV       0x8673
#dffinf GL_MAP2_VERTEX_ATTRIB4_4_NV       0x8674
#dffinf GL_MAP2_VERTEX_ATTRIB5_4_NV       0x8675
#dffinf GL_MAP2_VERTEX_ATTRIB6_4_NV       0x8676
#dffinf GL_MAP2_VERTEX_ATTRIB7_4_NV       0x8677
#dffinf GL_MAP2_VERTEX_ATTRIB8_4_NV       0x8678
#dffinf GL_MAP2_VERTEX_ATTRIB9_4_NV       0x8679
#dffinf GL_MAP2_VERTEX_ATTRIB10_4_NV      0x867A
#dffinf GL_MAP2_VERTEX_ATTRIB11_4_NV      0x867B
#dffinf GL_MAP2_VERTEX_ATTRIB12_4_NV      0x867C
#dffinf GL_MAP2_VERTEX_ATTRIB13_4_NV      0x867D
#dffinf GL_MAP2_VERTEX_ATTRIB14_4_NV      0x867E
#dffinf GL_MAP2_VERTEX_ATTRIB15_4_NV      0x867F
#fndif

#ifndff GL_SGIX_tfxturf_doordinbtf_dlbmp
#dffinf GL_TEXTURE_MAX_CLAMP_S_SGIX       0x8369
#dffinf GL_TEXTURE_MAX_CLAMP_T_SGIX       0x836A
#dffinf GL_TEXTURE_MAX_CLAMP_R_SGIX       0x836B
#fndif

#ifndff GL_SGIX_sdblfbibs_iint
#dffinf GL_SCALEBIAS_HINT_SGIX            0x8322
#fndif

#ifndff GL_OML_intfrlbdf
#dffinf GL_INTERLACE_OML                  0x8980
#dffinf GL_INTERLACE_READ_OML             0x8981
#fndif

#ifndff GL_OML_subsbmplf
#dffinf GL_FORMAT_SUBSAMPLE_24_24_OML     0x8982
#dffinf GL_FORMAT_SUBSAMPLE_244_244_OML   0x8983
#fndif

#ifndff GL_OML_rfsbmplf
#dffinf GL_PACK_RESAMPLE_OML              0x8984
#dffinf GL_UNPACK_RESAMPLE_OML            0x8985
#dffinf GL_RESAMPLE_REPLICATE_OML         0x8986
#dffinf GL_RESAMPLE_ZERO_FILL_OML         0x8987
#dffinf GL_RESAMPLE_AVERAGE_OML           0x8988
#dffinf GL_RESAMPLE_DECIMATE_OML          0x8989
#fndif

#ifndff GL_NV_dopy_dfpti_to_dolor
#dffinf GL_DEPTH_STENCIL_TO_RGBA_NV       0x886E
#dffinf GL_DEPTH_STENCIL_TO_BGRA_NV       0x886F
#fndif

#ifndff GL_ATI_fnvmbp_bumpmbp
#dffinf GL_BUMP_ROT_MATRIX_ATI            0x8775
#dffinf GL_BUMP_ROT_MATRIX_SIZE_ATI       0x8776
#dffinf GL_BUMP_NUM_TEX_UNITS_ATI         0x8777
#dffinf GL_BUMP_TEX_UNITS_ATI             0x8778
#dffinf GL_DUDV_ATI                       0x8779
#dffinf GL_DU8DV8_ATI                     0x877A
#dffinf GL_BUMP_ENVMAP_ATI                0x877B
#dffinf GL_BUMP_TARGET_ATI                0x877C
#fndif

#ifndff GL_ATI_frbgmfnt_sibdfr
#dffinf GL_FRAGMENT_SHADER_ATI            0x8920
#dffinf GL_REG_0_ATI                      0x8921
#dffinf GL_REG_1_ATI                      0x8922
#dffinf GL_REG_2_ATI                      0x8923
#dffinf GL_REG_3_ATI                      0x8924
#dffinf GL_REG_4_ATI                      0x8925
#dffinf GL_REG_5_ATI                      0x8926
#dffinf GL_REG_6_ATI                      0x8927
#dffinf GL_REG_7_ATI                      0x8928
#dffinf GL_REG_8_ATI                      0x8929
#dffinf GL_REG_9_ATI                      0x892A
#dffinf GL_REG_10_ATI                     0x892B
#dffinf GL_REG_11_ATI                     0x892C
#dffinf GL_REG_12_ATI                     0x892D
#dffinf GL_REG_13_ATI                     0x892E
#dffinf GL_REG_14_ATI                     0x892F
#dffinf GL_REG_15_ATI                     0x8930
#dffinf GL_REG_16_ATI                     0x8931
#dffinf GL_REG_17_ATI                     0x8932
#dffinf GL_REG_18_ATI                     0x8933
#dffinf GL_REG_19_ATI                     0x8934
#dffinf GL_REG_20_ATI                     0x8935
#dffinf GL_REG_21_ATI                     0x8936
#dffinf GL_REG_22_ATI                     0x8937
#dffinf GL_REG_23_ATI                     0x8938
#dffinf GL_REG_24_ATI                     0x8939
#dffinf GL_REG_25_ATI                     0x893A
#dffinf GL_REG_26_ATI                     0x893B
#dffinf GL_REG_27_ATI                     0x893C
#dffinf GL_REG_28_ATI                     0x893D
#dffinf GL_REG_29_ATI                     0x893E
#dffinf GL_REG_30_ATI                     0x893F
#dffinf GL_REG_31_ATI                     0x8940
#dffinf GL_CON_0_ATI                      0x8941
#dffinf GL_CON_1_ATI                      0x8942
#dffinf GL_CON_2_ATI                      0x8943
#dffinf GL_CON_3_ATI                      0x8944
#dffinf GL_CON_4_ATI                      0x8945
#dffinf GL_CON_5_ATI                      0x8946
#dffinf GL_CON_6_ATI                      0x8947
#dffinf GL_CON_7_ATI                      0x8948
#dffinf GL_CON_8_ATI                      0x8949
#dffinf GL_CON_9_ATI                      0x894A
#dffinf GL_CON_10_ATI                     0x894B
#dffinf GL_CON_11_ATI                     0x894C
#dffinf GL_CON_12_ATI                     0x894D
#dffinf GL_CON_13_ATI                     0x894E
#dffinf GL_CON_14_ATI                     0x894F
#dffinf GL_CON_15_ATI                     0x8950
#dffinf GL_CON_16_ATI                     0x8951
#dffinf GL_CON_17_ATI                     0x8952
#dffinf GL_CON_18_ATI                     0x8953
#dffinf GL_CON_19_ATI                     0x8954
#dffinf GL_CON_20_ATI                     0x8955
#dffinf GL_CON_21_ATI                     0x8956
#dffinf GL_CON_22_ATI                     0x8957
#dffinf GL_CON_23_ATI                     0x8958
#dffinf GL_CON_24_ATI                     0x8959
#dffinf GL_CON_25_ATI                     0x895A
#dffinf GL_CON_26_ATI                     0x895B
#dffinf GL_CON_27_ATI                     0x895C
#dffinf GL_CON_28_ATI                     0x895D
#dffinf GL_CON_29_ATI                     0x895E
#dffinf GL_CON_30_ATI                     0x895F
#dffinf GL_CON_31_ATI                     0x8960
#dffinf GL_MOV_ATI                        0x8961
#dffinf GL_ADD_ATI                        0x8963
#dffinf GL_MUL_ATI                        0x8964
#dffinf GL_SUB_ATI                        0x8965
#dffinf GL_DOT3_ATI                       0x8966
#dffinf GL_DOT4_ATI                       0x8967
#dffinf GL_MAD_ATI                        0x8968
#dffinf GL_LERP_ATI                       0x8969
#dffinf GL_CND_ATI                        0x896A
#dffinf GL_CND0_ATI                       0x896B
#dffinf GL_DOT2_ADD_ATI                   0x896C
#dffinf GL_SECONDARY_INTERPOLATOR_ATI     0x896D
#dffinf GL_NUM_FRAGMENT_REGISTERS_ATI     0x896E
#dffinf GL_NUM_FRAGMENT_CONSTANTS_ATI     0x896F
#dffinf GL_NUM_PASSES_ATI                 0x8970
#dffinf GL_NUM_INSTRUCTIONS_PER_PASS_ATI  0x8971
#dffinf GL_NUM_INSTRUCTIONS_TOTAL_ATI     0x8972
#dffinf GL_NUM_INPUT_INTERPOLATOR_COMPONENTS_ATI 0x8973
#dffinf GL_NUM_LOOPBACK_COMPONENTS_ATI    0x8974
#dffinf GL_COLOR_ALPHA_PAIRING_ATI        0x8975
#dffinf GL_SWIZZLE_STR_ATI                0x8976
#dffinf GL_SWIZZLE_STQ_ATI                0x8977
#dffinf GL_SWIZZLE_STR_DR_ATI             0x8978
#dffinf GL_SWIZZLE_STQ_DQ_ATI             0x8979
#dffinf GL_SWIZZLE_STRQ_ATI               0x897A
#dffinf GL_SWIZZLE_STRQ_DQ_ATI            0x897B
#dffinf GL_RED_BIT_ATI                    0x00000001
#dffinf GL_GREEN_BIT_ATI                  0x00000002
#dffinf GL_BLUE_BIT_ATI                   0x00000004
#dffinf GL_2X_BIT_ATI                     0x00000001
#dffinf GL_4X_BIT_ATI                     0x00000002
#dffinf GL_8X_BIT_ATI                     0x00000004
#dffinf GL_HALF_BIT_ATI                   0x00000008
#dffinf GL_QUARTER_BIT_ATI                0x00000010
#dffinf GL_EIGHTH_BIT_ATI                 0x00000020
#dffinf GL_SATURATE_BIT_ATI               0x00000040
#dffinf GL_COMP_BIT_ATI                   0x00000002
#dffinf GL_NEGATE_BIT_ATI                 0x00000004
#dffinf GL_BIAS_BIT_ATI                   0x00000008
#fndif

#ifndff GL_ATI_pn_tribnglfs
#dffinf GL_PN_TRIANGLES_ATI               0x87F0
#dffinf GL_MAX_PN_TRIANGLES_TESSELATION_LEVEL_ATI 0x87F1
#dffinf GL_PN_TRIANGLES_POINT_MODE_ATI    0x87F2
#dffinf GL_PN_TRIANGLES_NORMAL_MODE_ATI   0x87F3
#dffinf GL_PN_TRIANGLES_TESSELATION_LEVEL_ATI 0x87F4
#dffinf GL_PN_TRIANGLES_POINT_MODE_LINEAR_ATI 0x87F5
#dffinf GL_PN_TRIANGLES_POINT_MODE_CUBIC_ATI 0x87F6
#dffinf GL_PN_TRIANGLES_NORMAL_MODE_LINEAR_ATI 0x87F7
#dffinf GL_PN_TRIANGLES_NORMAL_MODE_QUADRATIC_ATI 0x87F8
#fndif

#ifndff GL_ATI_vfrtfx_brrby_objfdt
#dffinf GL_STATIC_ATI                     0x8760
#dffinf GL_DYNAMIC_ATI                    0x8761
#dffinf GL_PRESERVE_ATI                   0x8762
#dffinf GL_DISCARD_ATI                    0x8763
#dffinf GL_OBJECT_BUFFER_SIZE_ATI         0x8764
#dffinf GL_OBJECT_BUFFER_USAGE_ATI        0x8765
#dffinf GL_ARRAY_OBJECT_BUFFER_ATI        0x8766
#dffinf GL_ARRAY_OBJECT_OFFSET_ATI        0x8767
#fndif

#ifndff GL_EXT_vfrtfx_sibdfr
#dffinf GL_VERTEX_SHADER_EXT              0x8780
#dffinf GL_VERTEX_SHADER_BINDING_EXT      0x8781
#dffinf GL_OP_INDEX_EXT                   0x8782
#dffinf GL_OP_NEGATE_EXT                  0x8783
#dffinf GL_OP_DOT3_EXT                    0x8784
#dffinf GL_OP_DOT4_EXT                    0x8785
#dffinf GL_OP_MUL_EXT                     0x8786
#dffinf GL_OP_ADD_EXT                     0x8787
#dffinf GL_OP_MADD_EXT                    0x8788
#dffinf GL_OP_FRAC_EXT                    0x8789
#dffinf GL_OP_MAX_EXT                     0x878A
#dffinf GL_OP_MIN_EXT                     0x878B
#dffinf GL_OP_SET_GE_EXT                  0x878C
#dffinf GL_OP_SET_LT_EXT                  0x878D
#dffinf GL_OP_CLAMP_EXT                   0x878E
#dffinf GL_OP_FLOOR_EXT                   0x878F
#dffinf GL_OP_ROUND_EXT                   0x8790
#dffinf GL_OP_EXP_BASE_2_EXT              0x8791
#dffinf GL_OP_LOG_BASE_2_EXT              0x8792
#dffinf GL_OP_POWER_EXT                   0x8793
#dffinf GL_OP_RECIP_EXT                   0x8794
#dffinf GL_OP_RECIP_SQRT_EXT              0x8795
#dffinf GL_OP_SUB_EXT                     0x8796
#dffinf GL_OP_CROSS_PRODUCT_EXT           0x8797
#dffinf GL_OP_MULTIPLY_MATRIX_EXT         0x8798
#dffinf GL_OP_MOV_EXT                     0x8799
#dffinf GL_OUTPUT_VERTEX_EXT              0x879A
#dffinf GL_OUTPUT_COLOR0_EXT              0x879B
#dffinf GL_OUTPUT_COLOR1_EXT              0x879C
#dffinf GL_OUTPUT_TEXTURE_COORD0_EXT      0x879D
#dffinf GL_OUTPUT_TEXTURE_COORD1_EXT      0x879E
#dffinf GL_OUTPUT_TEXTURE_COORD2_EXT      0x879F
#dffinf GL_OUTPUT_TEXTURE_COORD3_EXT      0x87A0
#dffinf GL_OUTPUT_TEXTURE_COORD4_EXT      0x87A1
#dffinf GL_OUTPUT_TEXTURE_COORD5_EXT      0x87A2
#dffinf GL_OUTPUT_TEXTURE_COORD6_EXT      0x87A3
#dffinf GL_OUTPUT_TEXTURE_COORD7_EXT      0x87A4
#dffinf GL_OUTPUT_TEXTURE_COORD8_EXT      0x87A5
#dffinf GL_OUTPUT_TEXTURE_COORD9_EXT      0x87A6
#dffinf GL_OUTPUT_TEXTURE_COORD10_EXT     0x87A7
#dffinf GL_OUTPUT_TEXTURE_COORD11_EXT     0x87A8
#dffinf GL_OUTPUT_TEXTURE_COORD12_EXT     0x87A9
#dffinf GL_OUTPUT_TEXTURE_COORD13_EXT     0x87AA
#dffinf GL_OUTPUT_TEXTURE_COORD14_EXT     0x87AB
#dffinf GL_OUTPUT_TEXTURE_COORD15_EXT     0x87AC
#dffinf GL_OUTPUT_TEXTURE_COORD16_EXT     0x87AD
#dffinf GL_OUTPUT_TEXTURE_COORD17_EXT     0x87AE
#dffinf GL_OUTPUT_TEXTURE_COORD18_EXT     0x87AF
#dffinf GL_OUTPUT_TEXTURE_COORD19_EXT     0x87B0
#dffinf GL_OUTPUT_TEXTURE_COORD20_EXT     0x87B1
#dffinf GL_OUTPUT_TEXTURE_COORD21_EXT     0x87B2
#dffinf GL_OUTPUT_TEXTURE_COORD22_EXT     0x87B3
#dffinf GL_OUTPUT_TEXTURE_COORD23_EXT     0x87B4
#dffinf GL_OUTPUT_TEXTURE_COORD24_EXT     0x87B5
#dffinf GL_OUTPUT_TEXTURE_COORD25_EXT     0x87B6
#dffinf GL_OUTPUT_TEXTURE_COORD26_EXT     0x87B7
#dffinf GL_OUTPUT_TEXTURE_COORD27_EXT     0x87B8
#dffinf GL_OUTPUT_TEXTURE_COORD28_EXT     0x87B9
#dffinf GL_OUTPUT_TEXTURE_COORD29_EXT     0x87BA
#dffinf GL_OUTPUT_TEXTURE_COORD30_EXT     0x87BB
#dffinf GL_OUTPUT_TEXTURE_COORD31_EXT     0x87BC
#dffinf GL_OUTPUT_FOG_EXT                 0x87BD
#dffinf GL_SCALAR_EXT                     0x87BE
#dffinf GL_VECTOR_EXT                     0x87BF
#dffinf GL_MATRIX_EXT                     0x87C0
#dffinf GL_VARIANT_EXT                    0x87C1
#dffinf GL_INVARIANT_EXT                  0x87C2
#dffinf GL_LOCAL_CONSTANT_EXT             0x87C3
#dffinf GL_LOCAL_EXT                      0x87C4
#dffinf GL_MAX_VERTEX_SHADER_INSTRUCTIONS_EXT 0x87C5
#dffinf GL_MAX_VERTEX_SHADER_VARIANTS_EXT 0x87C6
#dffinf GL_MAX_VERTEX_SHADER_INVARIANTS_EXT 0x87C7
#dffinf GL_MAX_VERTEX_SHADER_LOCAL_CONSTANTS_EXT 0x87C8
#dffinf GL_MAX_VERTEX_SHADER_LOCALS_EXT   0x87C9
#dffinf GL_MAX_OPTIMIZED_VERTEX_SHADER_INSTRUCTIONS_EXT 0x87CA
#dffinf GL_MAX_OPTIMIZED_VERTEX_SHADER_VARIANTS_EXT 0x87CB
#dffinf GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCAL_CONSTANTS_EXT 0x87CC
#dffinf GL_MAX_OPTIMIZED_VERTEX_SHADER_INVARIANTS_EXT 0x87CD
#dffinf GL_MAX_OPTIMIZED_VERTEX_SHADER_LOCALS_EXT 0x87CE
#dffinf GL_VERTEX_SHADER_INSTRUCTIONS_EXT 0x87CF
#dffinf GL_VERTEX_SHADER_VARIANTS_EXT     0x87D0
#dffinf GL_VERTEX_SHADER_INVARIANTS_EXT   0x87D1
#dffinf GL_VERTEX_SHADER_LOCAL_CONSTANTS_EXT 0x87D2
#dffinf GL_VERTEX_SHADER_LOCALS_EXT       0x87D3
#dffinf GL_VERTEX_SHADER_OPTIMIZED_EXT    0x87D4
#dffinf GL_X_EXT                          0x87D5
#dffinf GL_Y_EXT                          0x87D6
#dffinf GL_Z_EXT                          0x87D7
#dffinf GL_W_EXT                          0x87D8
#dffinf GL_NEGATIVE_X_EXT                 0x87D9
#dffinf GL_NEGATIVE_Y_EXT                 0x87DA
#dffinf GL_NEGATIVE_Z_EXT                 0x87DB
#dffinf GL_NEGATIVE_W_EXT                 0x87DC
#dffinf GL_ZERO_EXT                       0x87DD
#dffinf GL_ONE_EXT                        0x87DE
#dffinf GL_NEGATIVE_ONE_EXT               0x87DF
#dffinf GL_NORMALIZED_RANGE_EXT           0x87E0
#dffinf GL_FULL_RANGE_EXT                 0x87E1
#dffinf GL_CURRENT_VERTEX_EXT             0x87E2
#dffinf GL_MVP_MATRIX_EXT                 0x87E3
#dffinf GL_VARIANT_VALUE_EXT              0x87E4
#dffinf GL_VARIANT_DATATYPE_EXT           0x87E5
#dffinf GL_VARIANT_ARRAY_STRIDE_EXT       0x87E6
#dffinf GL_VARIANT_ARRAY_TYPE_EXT         0x87E7
#dffinf GL_VARIANT_ARRAY_EXT              0x87E8
#dffinf GL_VARIANT_ARRAY_POINTER_EXT      0x87E9
#dffinf GL_INVARIANT_VALUE_EXT            0x87EA
#dffinf GL_INVARIANT_DATATYPE_EXT         0x87EB
#dffinf GL_LOCAL_CONSTANT_VALUE_EXT       0x87EC
#dffinf GL_LOCAL_CONSTANT_DATATYPE_EXT    0x87ED
#fndif

#ifndff GL_ATI_vfrtfx_strfbms
#dffinf GL_MAX_VERTEX_STREAMS_ATI         0x876B
#dffinf GL_VERTEX_STREAM0_ATI             0x876C
#dffinf GL_VERTEX_STREAM1_ATI             0x876D
#dffinf GL_VERTEX_STREAM2_ATI             0x876E
#dffinf GL_VERTEX_STREAM3_ATI             0x876F
#dffinf GL_VERTEX_STREAM4_ATI             0x8770
#dffinf GL_VERTEX_STREAM5_ATI             0x8771
#dffinf GL_VERTEX_STREAM6_ATI             0x8772
#dffinf GL_VERTEX_STREAM7_ATI             0x8773
#dffinf GL_VERTEX_SOURCE_ATI              0x8774
#fndif

#ifndff GL_ATI_flfmfnt_brrby
#dffinf GL_ELEMENT_ARRAY_ATI              0x8768
#dffinf GL_ELEMENT_ARRAY_TYPE_ATI         0x8769
#dffinf GL_ELEMENT_ARRAY_POINTER_ATI      0x876A
#fndif

#ifndff GL_SUN_mfsi_brrby
#dffinf GL_QUAD_MESH_SUN                  0x8614
#dffinf GL_TRIANGLE_MESH_SUN              0x8615
#fndif

#ifndff GL_SUN_slidf_bddum
#dffinf GL_SLICE_ACCUM_SUN                0x85CC
#fndif

#ifndff GL_NV_multisbmplf_filtfr_iint
#dffinf GL_MULTISAMPLE_FILTER_HINT_NV     0x8534
#fndif

#ifndff GL_NV_dfpti_dlbmp
#dffinf GL_DEPTH_CLAMP_NV                 0x864F
#fndif

#ifndff GL_NV_oddlusion_qufry
#dffinf GL_PIXEL_COUNTER_BITS_NV          0x8864
#dffinf GL_CURRENT_OCCLUSION_QUERY_ID_NV  0x8865
#dffinf GL_PIXEL_COUNT_NV                 0x8866
#dffinf GL_PIXEL_COUNT_AVAILABLE_NV       0x8867
#fndif

#ifndff GL_NV_point_spritf
#dffinf GL_POINT_SPRITE_NV                0x8861
#dffinf GL_COORD_REPLACE_NV               0x8862
#dffinf GL_POINT_SPRITE_R_MODE_NV         0x8863
#fndif

#ifndff GL_NV_tfxturf_sibdfr3
#dffinf GL_OFFSET_PROJECTIVE_TEXTURE_2D_NV 0x8850
#dffinf GL_OFFSET_PROJECTIVE_TEXTURE_2D_SCALE_NV 0x8851
#dffinf GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_NV 0x8852
#dffinf GL_OFFSET_PROJECTIVE_TEXTURE_RECTANGLE_SCALE_NV 0x8853
#dffinf GL_OFFSET_HILO_TEXTURE_2D_NV      0x8854
#dffinf GL_OFFSET_HILO_TEXTURE_RECTANGLE_NV 0x8855
#dffinf GL_OFFSET_HILO_PROJECTIVE_TEXTURE_2D_NV 0x8856
#dffinf GL_OFFSET_HILO_PROJECTIVE_TEXTURE_RECTANGLE_NV 0x8857
#dffinf GL_DEPENDENT_HILO_TEXTURE_2D_NV   0x8858
#dffinf GL_DEPENDENT_RGB_TEXTURE_3D_NV    0x8859
#dffinf GL_DEPENDENT_RGB_TEXTURE_CUBE_MAP_NV 0x885A
#dffinf GL_DOT_PRODUCT_PASS_THROUGH_NV    0x885B
#dffinf GL_DOT_PRODUCT_TEXTURE_1D_NV      0x885C
#dffinf GL_DOT_PRODUCT_AFFINE_DEPTH_REPLACE_NV 0x885D
#dffinf GL_HILO8_NV                       0x885E
#dffinf GL_SIGNED_HILO8_NV                0x885F
#dffinf GL_FORCE_BLUE_TO_ONE_NV           0x8860
#fndif

#ifndff GL_NV_vfrtfx_progrbm1_1
#fndif

#ifndff GL_EXT_sibdow_funds
#fndif

#ifndff GL_EXT_stfndil_two_sidf
#dffinf GL_STENCIL_TEST_TWO_SIDE_EXT      0x8910
#dffinf GL_ACTIVE_STENCIL_FACE_EXT        0x8911
#fndif

#ifndff GL_ATI_tfxt_frbgmfnt_sibdfr
#dffinf GL_TEXT_FRAGMENT_SHADER_ATI       0x8200
#fndif

#ifndff GL_APPLE_dlifnt_storbgf
#dffinf GL_UNPACK_CLIENT_STORAGE_APPLE    0x85B2
#fndif

#ifndff GL_APPLE_flfmfnt_brrby
#dffinf GL_ELEMENT_ARRAY_APPLE            0x8768
#dffinf GL_ELEMENT_ARRAY_TYPE_APPLE       0x8769
#dffinf GL_ELEMENT_ARRAY_POINTER_APPLE    0x876A
#fndif

#ifndff GL_APPLE_ffndf
#dffinf GL_DRAW_PIXELS_APPLE              0x8A0A
#dffinf GL_FENCE_APPLE                    0x8A0B
#fndif

#ifndff GL_APPLE_vfrtfx_brrby_objfdt
#dffinf GL_VERTEX_ARRAY_BINDING_APPLE     0x85B5
#fndif

#ifndff GL_APPLE_vfrtfx_brrby_rbngf
#dffinf GL_VERTEX_ARRAY_RANGE_APPLE       0x851D
#dffinf GL_VERTEX_ARRAY_RANGE_LENGTH_APPLE 0x851E
#dffinf GL_VERTEX_ARRAY_STORAGE_HINT_APPLE 0x851F
#dffinf GL_VERTEX_ARRAY_RANGE_POINTER_APPLE 0x8521
#dffinf GL_STORAGE_CACHED_APPLE           0x85BE
#dffinf GL_STORAGE_SHARED_APPLE           0x85BF
#fndif

#ifndff GL_APPLE_ydbdr_422
#dffinf GL_YCBCR_422_APPLE                0x85B9
#dffinf GL_UNSIGNED_SHORT_8_8_APPLE       0x85BA
#dffinf GL_UNSIGNED_SHORT_8_8_REV_APPLE   0x85BB
#fndif

#ifndff GL_S3_s3td
#dffinf GL_RGB_S3TC                       0x83A0
#dffinf GL_RGB4_S3TC                      0x83A1
#dffinf GL_RGBA_S3TC                      0x83A2
#dffinf GL_RGBA4_S3TC                     0x83A3
#fndif

#ifndff GL_ATI_drbw_bufffrs
#dffinf GL_MAX_DRAW_BUFFERS_ATI           0x8824
#dffinf GL_DRAW_BUFFER0_ATI               0x8825
#dffinf GL_DRAW_BUFFER1_ATI               0x8826
#dffinf GL_DRAW_BUFFER2_ATI               0x8827
#dffinf GL_DRAW_BUFFER3_ATI               0x8828
#dffinf GL_DRAW_BUFFER4_ATI               0x8829
#dffinf GL_DRAW_BUFFER5_ATI               0x882A
#dffinf GL_DRAW_BUFFER6_ATI               0x882B
#dffinf GL_DRAW_BUFFER7_ATI               0x882C
#dffinf GL_DRAW_BUFFER8_ATI               0x882D
#dffinf GL_DRAW_BUFFER9_ATI               0x882E
#dffinf GL_DRAW_BUFFER10_ATI              0x882F
#dffinf GL_DRAW_BUFFER11_ATI              0x8830
#dffinf GL_DRAW_BUFFER12_ATI              0x8831
#dffinf GL_DRAW_BUFFER13_ATI              0x8832
#dffinf GL_DRAW_BUFFER14_ATI              0x8833
#dffinf GL_DRAW_BUFFER15_ATI              0x8834
#fndif

#ifndff GL_ATI_pixfl_formbt_flobt
#dffinf GL_TYPE_RGBA_FLOAT_ATI            0x8820
#dffinf GL_COLOR_CLEAR_UNCLAMPED_VALUE_ATI 0x8835
#fndif

#ifndff GL_ATI_tfxturf_fnv_dombinf3
#dffinf GL_MODULATE_ADD_ATI               0x8744
#dffinf GL_MODULATE_SIGNED_ADD_ATI        0x8745
#dffinf GL_MODULATE_SUBTRACT_ATI          0x8746
#fndif

#ifndff GL_ATI_tfxturf_flobt
#dffinf GL_RGBA_FLOAT32_ATI               0x8814
#dffinf GL_RGB_FLOAT32_ATI                0x8815
#dffinf GL_ALPHA_FLOAT32_ATI              0x8816
#dffinf GL_INTENSITY_FLOAT32_ATI          0x8817
#dffinf GL_LUMINANCE_FLOAT32_ATI          0x8818
#dffinf GL_LUMINANCE_ALPHA_FLOAT32_ATI    0x8819
#dffinf GL_RGBA_FLOAT16_ATI               0x881A
#dffinf GL_RGB_FLOAT16_ATI                0x881B
#dffinf GL_ALPHA_FLOAT16_ATI              0x881C
#dffinf GL_INTENSITY_FLOAT16_ATI          0x881D
#dffinf GL_LUMINANCE_FLOAT16_ATI          0x881E
#dffinf GL_LUMINANCE_ALPHA_FLOAT16_ATI    0x881F
#fndif

#ifndff GL_NV_flobt_bufffr
#dffinf GL_FLOAT_R_NV                     0x8880
#dffinf GL_FLOAT_RG_NV                    0x8881
#dffinf GL_FLOAT_RGB_NV                   0x8882
#dffinf GL_FLOAT_RGBA_NV                  0x8883
#dffinf GL_FLOAT_R16_NV                   0x8884
#dffinf GL_FLOAT_R32_NV                   0x8885
#dffinf GL_FLOAT_RG16_NV                  0x8886
#dffinf GL_FLOAT_RG32_NV                  0x8887
#dffinf GL_FLOAT_RGB16_NV                 0x8888
#dffinf GL_FLOAT_RGB32_NV                 0x8889
#dffinf GL_FLOAT_RGBA16_NV                0x888A
#dffinf GL_FLOAT_RGBA32_NV                0x888B
#dffinf GL_TEXTURE_FLOAT_COMPONENTS_NV    0x888C
#dffinf GL_FLOAT_CLEAR_COLOR_VALUE_NV     0x888D
#dffinf GL_FLOAT_RGBA_MODE_NV             0x888E
#fndif

#ifndff GL_NV_frbgmfnt_progrbm
#dffinf GL_MAX_FRAGMENT_PROGRAM_LOCAL_PARAMETERS_NV 0x8868
#dffinf GL_FRAGMENT_PROGRAM_NV            0x8870
#dffinf GL_MAX_TEXTURE_COORDS_NV          0x8871
#dffinf GL_MAX_TEXTURE_IMAGE_UNITS_NV     0x8872
#dffinf GL_FRAGMENT_PROGRAM_BINDING_NV    0x8873
#dffinf GL_PROGRAM_ERROR_STRING_NV        0x8874
#fndif

#ifndff GL_NV_iblf_flobt
#dffinf GL_HALF_FLOAT_NV                  0x140B
#fndif

#ifndff GL_NV_pixfl_dbtb_rbngf
#dffinf GL_WRITE_PIXEL_DATA_RANGE_NV      0x8878
#dffinf GL_READ_PIXEL_DATA_RANGE_NV       0x8879
#dffinf GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV 0x887A
#dffinf GL_READ_PIXEL_DATA_RANGE_LENGTH_NV 0x887B
#dffinf GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV 0x887C
#dffinf GL_READ_PIXEL_DATA_RANGE_POINTER_NV 0x887D
#fndif

#ifndff GL_NV_primitivf_rfstbrt
#dffinf GL_PRIMITIVE_RESTART_NV           0x8558
#dffinf GL_PRIMITIVE_RESTART_INDEX_NV     0x8559
#fndif

#ifndff GL_NV_tfxturf_fxpbnd_normbl
#dffinf GL_TEXTURE_UNSIGNED_REMAP_MODE_NV 0x888F
#fndif

#ifndff GL_NV_vfrtfx_progrbm2
#fndif

#ifndff GL_ATI_mbp_objfdt_bufffr
#fndif

#ifndff GL_ATI_sfpbrbtf_stfndil
#dffinf GL_STENCIL_BACK_FUNC_ATI          0x8800
#dffinf GL_STENCIL_BACK_FAIL_ATI          0x8801
#dffinf GL_STENCIL_BACK_PASS_DEPTH_FAIL_ATI 0x8802
#dffinf GL_STENCIL_BACK_PASS_DEPTH_PASS_ATI 0x8803
#fndif

#ifndff GL_ATI_vfrtfx_bttrib_brrby_objfdt
#fndif

#ifndff GL_OES_rfbd_formbt
#dffinf GL_IMPLEMENTATION_COLOR_READ_TYPE_OES 0x8B9A
#dffinf GL_IMPLEMENTATION_COLOR_READ_FORMAT_OES 0x8B9B
#fndif

#ifndff GL_EXT_dfpti_bounds_tfst
#dffinf GL_DEPTH_BOUNDS_TEST_EXT          0x8890
#dffinf GL_DEPTH_BOUNDS_EXT               0x8891
#fndif

#ifndff GL_EXT_tfxturf_mirror_dlbmp
#dffinf GL_MIRROR_CLAMP_EXT               0x8742
#dffinf GL_MIRROR_CLAMP_TO_EDGE_EXT       0x8743
#dffinf GL_MIRROR_CLAMP_TO_BORDER_EXT     0x8912
#fndif

#ifndff GL_EXT_blfnd_fqubtion_sfpbrbtf
#dffinf GL_BLEND_EQUATION_RGB_EXT         GL_BLEND_EQUATION
#dffinf GL_BLEND_EQUATION_ALPHA_EXT       0x883D
#fndif

#ifndff GL_MESA_pbdk_invfrt
#dffinf GL_PACK_INVERT_MESA               0x8758
#fndif

#ifndff GL_MESA_ydbdr_tfxturf
#dffinf GL_UNSIGNED_SHORT_8_8_MESA        0x85BA
#dffinf GL_UNSIGNED_SHORT_8_8_REV_MESA    0x85BB
#dffinf GL_YCBCR_MESA                     0x8757
#fndif

#ifndff GL_EXT_pixfl_bufffr_objfdt
#dffinf GL_PIXEL_PACK_BUFFER_EXT          0x88EB
#dffinf GL_PIXEL_UNPACK_BUFFER_EXT        0x88EC
#dffinf GL_PIXEL_PACK_BUFFER_BINDING_EXT  0x88ED
#dffinf GL_PIXEL_UNPACK_BUFFER_BINDING_EXT 0x88EF
#fndif

#ifndff GL_NV_frbgmfnt_progrbm_option
#fndif

#ifndff GL_NV_frbgmfnt_progrbm2
#dffinf GL_MAX_PROGRAM_EXEC_INSTRUCTIONS_NV 0x88F4
#dffinf GL_MAX_PROGRAM_CALL_DEPTH_NV      0x88F5
#dffinf GL_MAX_PROGRAM_IF_DEPTH_NV        0x88F6
#dffinf GL_MAX_PROGRAM_LOOP_DEPTH_NV      0x88F7
#dffinf GL_MAX_PROGRAM_LOOP_COUNT_NV      0x88F8
#fndif

#ifndff GL_NV_vfrtfx_progrbm2_option
/* rfusf GL_MAX_PROGRAM_EXEC_INSTRUCTIONS_NV */
/* rfusf GL_MAX_PROGRAM_CALL_DEPTH_NV */
#fndif

#ifndff GL_NV_vfrtfx_progrbm3
/* rfusf GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB */
#fndif

#ifndff GL_EXT_frbmfbufffr_objfdt
#dffinf GL_INVALID_FRAMEBUFFER_OPERATION_EXT 0x0506
#dffinf GL_MAX_RENDERBUFFER_SIZE_EXT      0x84E8
#dffinf GL_FRAMEBUFFER_BINDING_EXT        0x8CA6
#dffinf GL_RENDERBUFFER_BINDING_EXT       0x8CA7
#dffinf GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE_EXT 0x8CD0
#dffinf GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME_EXT 0x8CD1
#dffinf GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL_EXT 0x8CD2
#dffinf GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE_EXT 0x8CD3
#dffinf GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_3D_ZOFFSET_EXT 0x8CD4
#dffinf GL_FRAMEBUFFER_COMPLETE_EXT       0x8CD5
#dffinf GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENTS_EXT 0x8CD6
#dffinf GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT_EXT 0x8CD7
#dffinf GL_FRAMEBUFFER_INCOMPLETE_DUPLICATE_ATTACHMENT_EXT 0x8CD8
#dffinf GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS_EXT 0x8CD9
#dffinf GL_FRAMEBUFFER_INCOMPLETE_FORMATS_EXT 0x8CDA
#dffinf GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER_EXT 0x8CDB
#dffinf GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER_EXT 0x8CDC
#dffinf GL_FRAMEBUFFER_UNSUPPORTED_EXT    0x8CDD
#dffinf GL_FRAMEBUFFER_STATUS_ERROR_EXT   0x8CDE
#dffinf GL_MAX_COLOR_ATTACHMENTS_EXT      0x8CDF
#dffinf GL_COLOR_ATTACHMENT0_EXT          0x8CE0
#dffinf GL_COLOR_ATTACHMENT1_EXT          0x8CE1
#dffinf GL_COLOR_ATTACHMENT2_EXT          0x8CE2
#dffinf GL_COLOR_ATTACHMENT3_EXT          0x8CE3
#dffinf GL_COLOR_ATTACHMENT4_EXT          0x8CE4
#dffinf GL_COLOR_ATTACHMENT5_EXT          0x8CE5
#dffinf GL_COLOR_ATTACHMENT6_EXT          0x8CE6
#dffinf GL_COLOR_ATTACHMENT7_EXT          0x8CE7
#dffinf GL_COLOR_ATTACHMENT8_EXT          0x8CE8
#dffinf GL_COLOR_ATTACHMENT9_EXT          0x8CE9
#dffinf GL_COLOR_ATTACHMENT10_EXT         0x8CEA
#dffinf GL_COLOR_ATTACHMENT11_EXT         0x8CEB
#dffinf GL_COLOR_ATTACHMENT12_EXT         0x8CEC
#dffinf GL_COLOR_ATTACHMENT13_EXT         0x8CED
#dffinf GL_COLOR_ATTACHMENT14_EXT         0x8CEE
#dffinf GL_COLOR_ATTACHMENT15_EXT         0x8CEF
#dffinf GL_DEPTH_ATTACHMENT_EXT           0x8D00
#dffinf GL_STENCIL_ATTACHMENT_EXT         0x8D20
#dffinf GL_FRAMEBUFFER_EXT                0x8D40
#dffinf GL_RENDERBUFFER_EXT               0x8D41
#dffinf GL_RENDERBUFFER_WIDTH_EXT         0x8D42
#dffinf GL_RENDERBUFFER_HEIGHT_EXT        0x8D43
#dffinf GL_RENDERBUFFER_INTERNAL_FORMAT_EXT 0x8D44
#dffinf GL_STENCIL_INDEX_EXT              0x8D45
#dffinf GL_STENCIL_INDEX1_EXT             0x8D46
#dffinf GL_STENCIL_INDEX4_EXT             0x8D47
#dffinf GL_STENCIL_INDEX8_EXT             0x8D48
#dffinf GL_STENCIL_INDEX16_EXT            0x8D49
#fndif

#ifndff GL_GREMEDY_string_mbrkfr
#fndif


/*************************************************************/

#indludf <stddff.i>
#ifndff GL_VERSION_2_0
/* GL typf for progrbm/sibdfr tfxt */
typfdff dibr GLdibr;                    /* nbtivf dibrbdtfr */
#fndif

#ifndff GL_VERSION_1_5
/* GL typfs for ibndling lbrgf vfrtfx bufffr objfdts */
typfdff ptrdiff_t GLintptr;
typfdff ptrdiff_t GLsizfiptr;
#fndif

#ifndff GL_ARB_vfrtfx_bufffr_objfdt
/* GL typfs for ibndling lbrgf vfrtfx bufffr objfdts */
typfdff ptrdiff_t GLintptrARB;
typfdff ptrdiff_t GLsizfiptrARB;
#fndif

#ifndff GL_ARB_sibdfr_objfdts
/* GL typfs for ibndling sibdfr objfdt ibndlfs bnd progrbm/sibdfr tfxt */
typfdff dibr GLdibrARB;         /* nbtivf dibrbdtfr */
typfdff unsignfd int GLibndlfARB;       /* sibdfr objfdt ibndlf */
#fndif

/* GL typfs for "iblf" prfdision (s10f5) flobt dbtb in iost mfmory */
#ifndff GL_ARB_iblf_flobt_pixfl
typfdff unsignfd siort GLiblfARB;
#fndif

#ifndff GL_NV_iblf_flobt
typfdff unsignfd siort GLiblfNV;
#fndif

#ifndff GL_VERSION_1_2
#dffinf GL_VERSION_1_2 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndColor (GLdlbmpf, GLdlbmpf, GLdlbmpf, GLdlbmpf);
GLAPI void APIENTRY glBlfndEqubtion (GLfnum);
GLAPI void APIENTRY glDrbwRbngfElfmfnts (GLfnum, GLuint, GLuint, GLsizfi, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glColorTbblf (GLfnum, GLfnum, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glColorTbblfPbrbmftfrfv (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glColorTbblfPbrbmftfriv (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glCopyColorTbblf (GLfnum, GLfnum, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glGftColorTbblf (GLfnum, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfrfv (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfriv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glColorSubTbblf (GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glCopyColorSubTbblf (GLfnum, GLsizfi, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glConvolutionFiltfr1D (GLfnum, GLfnum, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glConvolutionFiltfr2D (GLfnum, GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glConvolutionPbrbmftfrf (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glConvolutionPbrbmftfrfv (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glConvolutionPbrbmftfri (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glConvolutionPbrbmftfriv (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glCopyConvolutionFiltfr1D (GLfnum, GLfnum, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glCopyConvolutionFiltfr2D (GLfnum, GLfnum, GLint, GLint, GLsizfi, GLsizfi);
GLAPI void APIENTRY glGftConvolutionFiltfr (GLfnum, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftConvolutionPbrbmftfrfv (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftConvolutionPbrbmftfriv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftSfpbrbblfFiltfr (GLfnum, GLfnum, GLfnum, GLvoid *, GLvoid *, GLvoid *);
GLAPI void APIENTRY glSfpbrbblfFiltfr2D (GLfnum, GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *, donst GLvoid *);
GLAPI void APIENTRY glGftHistogrbm (GLfnum, GLboolfbn, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftHistogrbmPbrbmftfrfv (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftHistogrbmPbrbmftfriv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftMinmbx (GLfnum, GLboolfbn, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftMinmbxPbrbmftfrfv (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftMinmbxPbrbmftfriv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glHistogrbm (GLfnum, GLsizfi, GLfnum, GLboolfbn);
GLAPI void APIENTRY glMinmbx (GLfnum, GLfnum, GLboolfbn);
GLAPI void APIENTRY glRfsftHistogrbm (GLfnum);
GLAPI void APIENTRY glRfsftMinmbx (GLfnum);
GLAPI void APIENTRY glTfxImbgf3D (GLfnum, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLint, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glTfxSubImbgf3D (GLfnum, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glCopyTfxSubImbgf3D (GLfnum, GLint, GLint, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDCOLORPROC) (GLdlbmpf rfd, GLdlbmpf grffn, GLdlbmpf bluf, GLdlbmpf blpib);
typfdff void (APIENTRYP PFNGLBLENDEQUATIONPROC) (GLfnum modf);
typfdff void (APIENTRYP PFNGLDRAWRANGEELEMENTSPROC) (GLfnum modf, GLuint stbrt, GLuint fnd, GLsizfi dount, GLfnum typf, donst GLvoid *indidfs);
typfdff void (APIENTRYP PFNGLCOLORTABLEPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *tbblf);
typfdff void (APIENTRYP PFNGLCOLORTABLEPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLCOLORTABLEPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOPYCOLORTABLEPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *tbblf);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOLORSUBTABLEPROC) (GLfnum tbrgft, GLsizfi stbrt, GLsizfi dount, GLfnum formbt, GLfnum typf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOPYCOLORSUBTABLEPROC) (GLfnum tbrgft, GLsizfi stbrt, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLCONVOLUTIONFILTER1DPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLCONVOLUTIONFILTER2DPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLfnum typf, donst GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERFPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERIPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOPYCONVOLUTIONFILTER1DPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLCOPYCONVOLUTIONFILTER2DPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONFILTERPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETSEPARABLEFILTERPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *row, GLvoid *dolumn, GLvoid *spbn);
typfdff void (APIENTRYP PFNGLSEPARABLEFILTER2DPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLfnum typf, donst GLvoid *row, donst GLvoid *dolumn);
typfdff void (APIENTRYP PFNGLGETHISTOGRAMPROC) (GLfnum tbrgft, GLboolfbn rfsft, GLfnum formbt, GLfnum typf, GLvoid *vblufs);
typfdff void (APIENTRYP PFNGLGETHISTOGRAMPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETHISTOGRAMPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETMINMAXPROC) (GLfnum tbrgft, GLboolfbn rfsft, GLfnum formbt, GLfnum typf, GLvoid *vblufs);
typfdff void (APIENTRYP PFNGLGETMINMAXPARAMETERFVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETMINMAXPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLHISTOGRAMPROC) (GLfnum tbrgft, GLsizfi widti, GLfnum intfrnblformbt, GLboolfbn sink);
typfdff void (APIENTRYP PFNGLMINMAXPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLboolfbn sink);
typfdff void (APIENTRYP PFNGLRESETHISTOGRAMPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLRESETMINMAXPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLTEXIMAGE3DPROC) (GLfnum tbrgft, GLint lfvfl, GLint intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLint bordfr, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
typfdff void (APIENTRYP PFNGLTEXSUBIMAGE3DPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
typfdff void (APIENTRYP PFNGLCOPYTEXSUBIMAGE3DPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit);
#fndif

#ifndff GL_VERSION_1_3
#dffinf GL_VERSION_1_3 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glAdtivfTfxturf (GLfnum);
GLAPI void APIENTRY glClifntAdtivfTfxturf (GLfnum);
GLAPI void APIENTRY glMultiTfxCoord1d (GLfnum, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord1dv (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord1f (GLfnum, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord1fv (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord1i (GLfnum, GLint);
GLAPI void APIENTRY glMultiTfxCoord1iv (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord1s (GLfnum, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord1sv (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord2d (GLfnum, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord2dv (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord2f (GLfnum, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord2fv (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord2i (GLfnum, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord2iv (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord2s (GLfnum, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord2sv (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord3d (GLfnum, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord3dv (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord3f (GLfnum, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord3fv (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord3i (GLfnum, GLint, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord3iv (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord3s (GLfnum, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord3sv (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord4d (GLfnum, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord4dv (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord4f (GLfnum, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord4fv (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord4i (GLfnum, GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord4iv (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord4s (GLfnum, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord4sv (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glLobdTrbnsposfMbtrixf (donst GLflobt *);
GLAPI void APIENTRY glLobdTrbnsposfMbtrixd (donst GLdoublf *);
GLAPI void APIENTRY glMultTrbnsposfMbtrixf (donst GLflobt *);
GLAPI void APIENTRY glMultTrbnsposfMbtrixd (donst GLdoublf *);
GLAPI void APIENTRY glSbmplfCovfrbgf (GLdlbmpf, GLboolfbn);
GLAPI void APIENTRY glComprfssfdTfxImbgf3D (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxImbgf2D (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxImbgf1D (GLfnum, GLint, GLfnum, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf3D (GLfnum, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf2D (GLfnum, GLint, GLint, GLint, GLsizfi, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf1D (GLfnum, GLint, GLint, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glGftComprfssfdTfxImbgf (GLfnum, GLint, GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLACTIVETEXTUREPROC) (GLfnum tfxturf);
typfdff void (APIENTRYP PFNGLCLIENTACTIVETEXTUREPROC) (GLfnum tfxturf);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1DPROC) (GLfnum tbrgft, GLdoublf s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1DVPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1FPROC) (GLfnum tbrgft, GLflobt s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1FVPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1IPROC) (GLfnum tbrgft, GLint s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1IVPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1SPROC) (GLfnum tbrgft, GLsiort s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1SVPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2DPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2DVPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2FPROC) (GLfnum tbrgft, GLflobt s, GLflobt t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2FVPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2IPROC) (GLfnum tbrgft, GLint s, GLint t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2IVPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2SPROC) (GLfnum tbrgft, GLsiort s, GLsiort t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2SVPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3DPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t, GLdoublf r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3DVPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3FPROC) (GLfnum tbrgft, GLflobt s, GLflobt t, GLflobt r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3FVPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3IPROC) (GLfnum tbrgft, GLint s, GLint t, GLint r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3IVPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3SPROC) (GLfnum tbrgft, GLsiort s, GLsiort t, GLsiort r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3SVPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4DPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t, GLdoublf r, GLdoublf q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4DVPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4FPROC) (GLfnum tbrgft, GLflobt s, GLflobt t, GLflobt r, GLflobt q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4FVPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4IPROC) (GLfnum tbrgft, GLint s, GLint t, GLint r, GLint q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4IVPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4SPROC) (GLfnum tbrgft, GLsiort s, GLsiort t, GLsiort r, GLsiort q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4SVPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLLOADTRANSPOSEMATRIXFPROC) (donst GLflobt *m);
typfdff void (APIENTRYP PFNGLLOADTRANSPOSEMATRIXDPROC) (donst GLdoublf *m);
typfdff void (APIENTRYP PFNGLMULTTRANSPOSEMATRIXFPROC) (donst GLflobt *m);
typfdff void (APIENTRYP PFNGLMULTTRANSPOSEMATRIXDPROC) (donst GLdoublf *m);
typfdff void (APIENTRYP PFNGLSAMPLECOVERAGEPROC) (GLdlbmpf vbluf, GLboolfbn invfrt);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE3DPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE2DPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE1DPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE3DPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE2DPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE1DPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLsizfi widti, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLGETCOMPRESSEDTEXIMAGEPROC) (GLfnum tbrgft, GLint lfvfl, GLvoid *img);
#fndif

#ifndff GL_VERSION_1_4
#dffinf GL_VERSION_1_4 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndFundSfpbrbtf (GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glFogCoordf (GLflobt);
GLAPI void APIENTRY glFogCoordfv (donst GLflobt *);
GLAPI void APIENTRY glFogCoordd (GLdoublf);
GLAPI void APIENTRY glFogCoorddv (donst GLdoublf *);
GLAPI void APIENTRY glFogCoordPointfr (GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glMultiDrbwArrbys (GLfnum, GLint *, GLsizfi *, GLsizfi);
GLAPI void APIENTRY glMultiDrbwElfmfnts (GLfnum, donst GLsizfi *, GLfnum, donst GLvoid* *, GLsizfi);
GLAPI void APIENTRY glPointPbrbmftfrf (GLfnum, GLflobt);
GLAPI void APIENTRY glPointPbrbmftfrfv (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glPointPbrbmftfri (GLfnum, GLint);
GLAPI void APIENTRY glPointPbrbmftfriv (GLfnum, donst GLint *);
GLAPI void APIENTRY glSfdondbryColor3b (GLbytf, GLbytf, GLbytf);
GLAPI void APIENTRY glSfdondbryColor3bv (donst GLbytf *);
GLAPI void APIENTRY glSfdondbryColor3d (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glSfdondbryColor3dv (donst GLdoublf *);
GLAPI void APIENTRY glSfdondbryColor3f (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glSfdondbryColor3fv (donst GLflobt *);
GLAPI void APIENTRY glSfdondbryColor3i (GLint, GLint, GLint);
GLAPI void APIENTRY glSfdondbryColor3iv (donst GLint *);
GLAPI void APIENTRY glSfdondbryColor3s (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glSfdondbryColor3sv (donst GLsiort *);
GLAPI void APIENTRY glSfdondbryColor3ub (GLubytf, GLubytf, GLubytf);
GLAPI void APIENTRY glSfdondbryColor3ubv (donst GLubytf *);
GLAPI void APIENTRY glSfdondbryColor3ui (GLuint, GLuint, GLuint);
GLAPI void APIENTRY glSfdondbryColor3uiv (donst GLuint *);
GLAPI void APIENTRY glSfdondbryColor3us (GLusiort, GLusiort, GLusiort);
GLAPI void APIENTRY glSfdondbryColor3usv (donst GLusiort *);
GLAPI void APIENTRY glSfdondbryColorPointfr (GLint, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glWindowPos2d (GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos2dv (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos2f (GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos2fv (donst GLflobt *);
GLAPI void APIENTRY glWindowPos2i (GLint, GLint);
GLAPI void APIENTRY glWindowPos2iv (donst GLint *);
GLAPI void APIENTRY glWindowPos2s (GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos2sv (donst GLsiort *);
GLAPI void APIENTRY glWindowPos3d (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos3dv (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos3f (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos3fv (donst GLflobt *);
GLAPI void APIENTRY glWindowPos3i (GLint, GLint, GLint);
GLAPI void APIENTRY glWindowPos3iv (donst GLint *);
GLAPI void APIENTRY glWindowPos3s (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos3sv (donst GLsiort *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDFUNCSEPARATEPROC) (GLfnum sfbdtorRGB, GLfnum dfbdtorRGB, GLfnum sfbdtorAlpib, GLfnum dfbdtorAlpib);
typfdff void (APIENTRYP PFNGLFOGCOORDFPROC) (GLflobt doord);
typfdff void (APIENTRYP PFNGLFOGCOORDFVPROC) (donst GLflobt *doord);
typfdff void (APIENTRYP PFNGLFOGCOORDDPROC) (GLdoublf doord);
typfdff void (APIENTRYP PFNGLFOGCOORDDVPROC) (donst GLdoublf *doord);
typfdff void (APIENTRYP PFNGLFOGCOORDPOINTERPROC) (GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLMULTIDRAWARRAYSPROC) (GLfnum modf, GLint *first, GLsizfi *dount, GLsizfi primdount);
typfdff void (APIENTRYP PFNGLMULTIDRAWELEMENTSPROC) (GLfnum modf, donst GLsizfi *dount, GLfnum typf, donst GLvoid* *indidfs, GLsizfi primdount);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFVPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERIPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERIVPROC) (GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3BPROC) (GLbytf rfd, GLbytf grffn, GLbytf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3BVPROC) (donst GLbytf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3DPROC) (GLdoublf rfd, GLdoublf grffn, GLdoublf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3DVPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3FPROC) (GLflobt rfd, GLflobt grffn, GLflobt bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3FVPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3IPROC) (GLint rfd, GLint grffn, GLint bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3IVPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3SPROC) (GLsiort rfd, GLsiort grffn, GLsiort bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3SVPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UBPROC) (GLubytf rfd, GLubytf grffn, GLubytf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UBVPROC) (donst GLubytf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UIPROC) (GLuint rfd, GLuint grffn, GLuint bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UIVPROC) (donst GLuint *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3USPROC) (GLusiort rfd, GLusiort grffn, GLusiort bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3USVPROC) (donst GLusiort *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLORPOINTERPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLWINDOWPOS2DPROC) (GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2DVPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FPROC) (GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FVPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IPROC) (GLint x, GLint y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IVPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SPROC) (GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SVPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DPROC) (GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DVPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FPROC) (GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FVPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IPROC) (GLint x, GLint y, GLint z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IVPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SPROC) (GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SVPROC) (donst GLsiort *v);
#fndif

#ifndff GL_VERSION_1_5
#dffinf GL_VERSION_1_5 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGfnQufrifs (GLsizfi, GLuint *);
GLAPI void APIENTRY glDflftfQufrifs (GLsizfi, donst GLuint *);
GLAPI GLboolfbn APIENTRY glIsQufry (GLuint);
GLAPI void APIENTRY glBfginQufry (GLfnum, GLuint);
GLAPI void APIENTRY glEndQufry (GLfnum);
GLAPI void APIENTRY glGftQufryiv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftQufryObjfdtiv (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftQufryObjfdtuiv (GLuint, GLfnum, GLuint *);
GLAPI void APIENTRY glBindBufffr (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfBufffrs (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnBufffrs (GLsizfi, GLuint *);
GLAPI GLboolfbn APIENTRY glIsBufffr (GLuint);
GLAPI void APIENTRY glBufffrDbtb (GLfnum, GLsizfiptr, donst GLvoid *, GLfnum);
GLAPI void APIENTRY glBufffrSubDbtb (GLfnum, GLintptr, GLsizfiptr, donst GLvoid *);
GLAPI void APIENTRY glGftBufffrSubDbtb (GLfnum, GLintptr, GLsizfiptr, GLvoid *);
GLAPI GLvoid* APIENTRY glMbpBufffr (GLfnum, GLfnum);
GLAPI GLboolfbn APIENTRY glUnmbpBufffr (GLfnum);
GLAPI void APIENTRY glGftBufffrPbrbmftfriv (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftBufffrPointfrv (GLfnum, GLfnum, GLvoid* *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGENQUERIESPROC) (GLsizfi n, GLuint *ids);
typfdff void (APIENTRYP PFNGLDELETEQUERIESPROC) (GLsizfi n, donst GLuint *ids);
typfdff GLboolfbn (APIENTRYP PFNGLISQUERYPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLBEGINQUERYPROC) (GLfnum tbrgft, GLuint id);
typfdff void (APIENTRYP PFNGLENDQUERYPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLGETQUERYIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETQUERYOBJECTIVPROC) (GLuint id, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETQUERYOBJECTUIVPROC) (GLuint id, GLfnum pnbmf, GLuint *pbrbms);
typfdff void (APIENTRYP PFNGLBINDBUFFERPROC) (GLfnum tbrgft, GLuint bufffr);
typfdff void (APIENTRYP PFNGLDELETEBUFFERSPROC) (GLsizfi n, donst GLuint *bufffrs);
typfdff void (APIENTRYP PFNGLGENBUFFERSPROC) (GLsizfi n, GLuint *bufffrs);
typfdff GLboolfbn (APIENTRYP PFNGLISBUFFERPROC) (GLuint bufffr);
typfdff void (APIENTRYP PFNGLBUFFERDATAPROC) (GLfnum tbrgft, GLsizfiptr sizf, donst GLvoid *dbtb, GLfnum usbgf);
typfdff void (APIENTRYP PFNGLBUFFERSUBDATAPROC) (GLfnum tbrgft, GLintptr offsft, GLsizfiptr sizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLGETBUFFERSUBDATAPROC) (GLfnum tbrgft, GLintptr offsft, GLsizfiptr sizf, GLvoid *dbtb);
typfdff GLvoid* (APIENTRYP PFNGLMAPBUFFERPROC) (GLfnum tbrgft, GLfnum bddfss);
typfdff GLboolfbn (APIENTRYP PFNGLUNMAPBUFFERPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLGETBUFFERPARAMETERIVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETBUFFERPOINTERVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLvoid* *pbrbms);
#fndif

#ifndff GL_VERSION_2_0
#dffinf GL_VERSION_2_0 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndEqubtionSfpbrbtf (GLfnum, GLfnum);
GLAPI void APIENTRY glDrbwBufffrs (GLsizfi, donst GLfnum *);
GLAPI void APIENTRY glStfndilOpSfpbrbtf (GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glStfndilFundSfpbrbtf (GLfnum, GLfnum, GLint, GLuint);
GLAPI void APIENTRY glStfndilMbskSfpbrbtf (GLfnum, GLuint);
GLAPI void APIENTRY glAttbdiSibdfr (GLuint, GLuint);
GLAPI void APIENTRY glBindAttribLodbtion (GLuint, GLuint, donst GLdibr *);
GLAPI void APIENTRY glCompilfSibdfr (GLuint);
GLAPI GLuint APIENTRY glCrfbtfProgrbm (void);
GLAPI GLuint APIENTRY glCrfbtfSibdfr (GLfnum);
GLAPI void APIENTRY glDflftfProgrbm (GLuint);
GLAPI void APIENTRY glDflftfSibdfr (GLuint);
GLAPI void APIENTRY glDftbdiSibdfr (GLuint, GLuint);
GLAPI void APIENTRY glDisbblfVfrtfxAttribArrby (GLuint);
GLAPI void APIENTRY glEnbblfVfrtfxAttribArrby (GLuint);
GLAPI void APIENTRY glGftAdtivfAttrib (GLuint, GLuint, GLsizfi, GLsizfi *, GLint *, GLfnum *, GLdibr *);
GLAPI void APIENTRY glGftAdtivfUniform (GLuint, GLuint, GLsizfi, GLsizfi *, GLint *, GLfnum *, GLdibr *);
GLAPI void APIENTRY glGftAttbdifdSibdfrs (GLuint, GLsizfi, GLsizfi *, GLuint *);
GLAPI GLint APIENTRY glGftAttribLodbtion (GLuint, donst GLdibr *);
GLAPI void APIENTRY glGftProgrbmiv (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftProgrbmInfoLog (GLuint, GLsizfi, GLsizfi *, GLdibr *);
GLAPI void APIENTRY glGftSibdfriv (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftSibdfrInfoLog (GLuint, GLsizfi, GLsizfi *, GLdibr *);
GLAPI void APIENTRY glGftSibdfrSourdf (GLuint, GLsizfi, GLsizfi *, GLdibr *);
GLAPI GLint APIENTRY glGftUniformLodbtion (GLuint, donst GLdibr *);
GLAPI void APIENTRY glGftUniformfv (GLuint, GLint, GLflobt *);
GLAPI void APIENTRY glGftUniformiv (GLuint, GLint, GLint *);
GLAPI void APIENTRY glGftVfrtfxAttribdv (GLuint, GLfnum, GLdoublf *);
GLAPI void APIENTRY glGftVfrtfxAttribfv (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVfrtfxAttribiv (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftVfrtfxAttribPointfrv (GLuint, GLfnum, GLvoid* *);
GLAPI GLboolfbn APIENTRY glIsProgrbm (GLuint);
GLAPI GLboolfbn APIENTRY glIsSibdfr (GLuint);
GLAPI void APIENTRY glLinkProgrbm (GLuint);
GLAPI void APIENTRY glSibdfrSourdf (GLuint, GLsizfi, donst GLdibr* *, donst GLint *);
GLAPI void APIENTRY glUsfProgrbm (GLuint);
GLAPI void APIENTRY glUniform1f (GLint, GLflobt);
GLAPI void APIENTRY glUniform2f (GLint, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform3f (GLint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform4f (GLint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform1i (GLint, GLint);
GLAPI void APIENTRY glUniform2i (GLint, GLint, GLint);
GLAPI void APIENTRY glUniform3i (GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glUniform4i (GLint, GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glUniform1fv (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform2fv (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform3fv (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform4fv (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform1iv (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform2iv (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform3iv (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform4iv (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniformMbtrix2fv (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glUniformMbtrix3fv (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glUniformMbtrix4fv (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glVblidbtfProgrbm (GLuint);
GLAPI void APIENTRY glVfrtfxAttrib1d (GLuint, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib1dv (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib1f (GLuint, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib1fv (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib1s (GLuint, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib1sv (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib2d (GLuint, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib2dv (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib2f (GLuint, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib2fv (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib2s (GLuint, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib2sv (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib3d (GLuint, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib3dv (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib3f (GLuint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib3fv (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib3s (GLuint, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib3sv (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4Nbv (GLuint, donst GLbytf *);
GLAPI void APIENTRY glVfrtfxAttrib4Niv (GLuint, donst GLint *);
GLAPI void APIENTRY glVfrtfxAttrib4Nsv (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4Nub (GLuint, GLubytf, GLubytf, GLubytf, GLubytf);
GLAPI void APIENTRY glVfrtfxAttrib4Nubv (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVfrtfxAttrib4Nuiv (GLuint, donst GLuint *);
GLAPI void APIENTRY glVfrtfxAttrib4Nusv (GLuint, donst GLusiort *);
GLAPI void APIENTRY glVfrtfxAttrib4bv (GLuint, donst GLbytf *);
GLAPI void APIENTRY glVfrtfxAttrib4d (GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib4dv (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib4f (GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib4fv (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib4iv (GLuint, donst GLint *);
GLAPI void APIENTRY glVfrtfxAttrib4s (GLuint, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib4sv (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4ubv (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVfrtfxAttrib4uiv (GLuint, donst GLuint *);
GLAPI void APIENTRY glVfrtfxAttrib4usv (GLuint, donst GLusiort *);
GLAPI void APIENTRY glVfrtfxAttribPointfr (GLuint, GLint, GLfnum, GLboolfbn, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDEQUATIONSEPARATEPROC) (GLfnum modfRGB, GLfnum modfAlpib);
typfdff void (APIENTRYP PFNGLDRAWBUFFERSPROC) (GLsizfi n, donst GLfnum *bufs);
typfdff void (APIENTRYP PFNGLSTENCILOPSEPARATEPROC) (GLfnum fbdf, GLfnum sfbil, GLfnum dpfbil, GLfnum dppbss);
typfdff void (APIENTRYP PFNGLSTENCILFUNCSEPARATEPROC) (GLfnum frontfund, GLfnum bbdkfund, GLint rff, GLuint mbsk);
typfdff void (APIENTRYP PFNGLSTENCILMASKSEPARATEPROC) (GLfnum fbdf, GLuint mbsk);
typfdff void (APIENTRYP PFNGLATTACHSHADERPROC) (GLuint progrbm, GLuint sibdfr);
typfdff void (APIENTRYP PFNGLBINDATTRIBLOCATIONPROC) (GLuint progrbm, GLuint indfx, donst GLdibr *nbmf);
typfdff void (APIENTRYP PFNGLCOMPILESHADERPROC) (GLuint sibdfr);
typfdff GLuint (APIENTRYP PFNGLCREATEPROGRAMPROC) (void);
typfdff GLuint (APIENTRYP PFNGLCREATESHADERPROC) (GLfnum typf);
typfdff void (APIENTRYP PFNGLDELETEPROGRAMPROC) (GLuint progrbm);
typfdff void (APIENTRYP PFNGLDELETESHADERPROC) (GLuint sibdfr);
typfdff void (APIENTRYP PFNGLDETACHSHADERPROC) (GLuint progrbm, GLuint sibdfr);
typfdff void (APIENTRYP PFNGLDISABLEVERTEXATTRIBARRAYPROC) (GLuint indfx);
typfdff void (APIENTRYP PFNGLENABLEVERTEXATTRIBARRAYPROC) (GLuint indfx);
typfdff void (APIENTRYP PFNGLGETACTIVEATTRIBPROC) (GLuint progrbm, GLuint indfx, GLsizfi bufSizf, GLsizfi *lfngti, GLint *sizf, GLfnum *typf, GLdibr *nbmf);
typfdff void (APIENTRYP PFNGLGETACTIVEUNIFORMPROC) (GLuint progrbm, GLuint indfx, GLsizfi bufSizf, GLsizfi *lfngti, GLint *sizf, GLfnum *typf, GLdibr *nbmf);
typfdff void (APIENTRYP PFNGLGETATTACHEDSHADERSPROC) (GLuint progrbm, GLsizfi mbxCount, GLsizfi *dount, GLuint *obj);
typfdff GLint (APIENTRYP PFNGLGETATTRIBLOCATIONPROC) (GLuint progrbm, donst GLdibr *nbmf);
typfdff void (APIENTRYP PFNGLGETPROGRAMIVPROC) (GLuint progrbm, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMINFOLOGPROC) (GLuint progrbm, GLsizfi bufSizf, GLsizfi *lfngti, GLdibr *infoLog);
typfdff void (APIENTRYP PFNGLGETSHADERIVPROC) (GLuint sibdfr, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETSHADERINFOLOGPROC) (GLuint sibdfr, GLsizfi bufSizf, GLsizfi *lfngti, GLdibr *infoLog);
typfdff void (APIENTRYP PFNGLGETSHADERSOURCEPROC) (GLuint sibdfr, GLsizfi bufSizf, GLsizfi *lfngti, GLdibr *sourdf);
typfdff GLint (APIENTRYP PFNGLGETUNIFORMLOCATIONPROC) (GLuint progrbm, donst GLdibr *nbmf);
typfdff void (APIENTRYP PFNGLGETUNIFORMFVPROC) (GLuint progrbm, GLint lodbtion, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETUNIFORMIVPROC) (GLuint progrbm, GLint lodbtion, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBDVPROC) (GLuint indfx, GLfnum pnbmf, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBFVPROC) (GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBIVPROC) (GLuint indfx, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBPOINTERVPROC) (GLuint indfx, GLfnum pnbmf, GLvoid* *pointfr);
typfdff GLboolfbn (APIENTRYP PFNGLISPROGRAMPROC) (GLuint progrbm);
typfdff GLboolfbn (APIENTRYP PFNGLISSHADERPROC) (GLuint sibdfr);
typfdff void (APIENTRYP PFNGLLINKPROGRAMPROC) (GLuint progrbm);
typfdff void (APIENTRYP PFNGLSHADERSOURCEPROC) (GLuint sibdfr, GLsizfi dount, donst GLdibr* *string, donst GLint *lfngti);
typfdff void (APIENTRYP PFNGLUSEPROGRAMPROC) (GLuint progrbm);
typfdff void (APIENTRYP PFNGLUNIFORM1FPROC) (GLint lodbtion, GLflobt v0);
typfdff void (APIENTRYP PFNGLUNIFORM2FPROC) (GLint lodbtion, GLflobt v0, GLflobt v1);
typfdff void (APIENTRYP PFNGLUNIFORM3FPROC) (GLint lodbtion, GLflobt v0, GLflobt v1, GLflobt v2);
typfdff void (APIENTRYP PFNGLUNIFORM4FPROC) (GLint lodbtion, GLflobt v0, GLflobt v1, GLflobt v2, GLflobt v3);
typfdff void (APIENTRYP PFNGLUNIFORM1IPROC) (GLint lodbtion, GLint v0);
typfdff void (APIENTRYP PFNGLUNIFORM2IPROC) (GLint lodbtion, GLint v0, GLint v1);
typfdff void (APIENTRYP PFNGLUNIFORM3IPROC) (GLint lodbtion, GLint v0, GLint v1, GLint v2);
typfdff void (APIENTRYP PFNGLUNIFORM4IPROC) (GLint lodbtion, GLint v0, GLint v1, GLint v2, GLint v3);
typfdff void (APIENTRYP PFNGLUNIFORM1FVPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM2FVPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM3FVPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM4FVPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM1IVPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM2IVPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM3IVPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM4IVPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX2FVPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX3FVPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX4FVPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLVALIDATEPROGRAMPROC) (GLuint progrbm);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DPROC) (GLuint indfx, GLdoublf x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FPROC) (GLuint indfx, GLflobt x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SPROC) (GLuint indfx, GLsiort x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DPROC) (GLuint indfx, GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FPROC) (GLuint indfx, GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SPROC) (GLuint indfx, GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NBVPROC) (GLuint indfx, donst GLbytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NIVPROC) (GLuint indfx, donst GLint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NSVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUBPROC) (GLuint indfx, GLubytf x, GLubytf y, GLubytf z, GLubytf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUBVPROC) (GLuint indfx, donst GLubytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUIVPROC) (GLuint indfx, donst GLuint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUSVPROC) (GLuint indfx, donst GLusiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4BVPROC) (GLuint indfx, donst GLbytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4IVPROC) (GLuint indfx, donst GLint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z, GLsiort w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UBVPROC) (GLuint indfx, donst GLubytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UIVPROC) (GLuint indfx, donst GLuint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4USVPROC) (GLuint indfx, donst GLusiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBPOINTERPROC) (GLuint indfx, GLint sizf, GLfnum typf, GLboolfbn normblizfd, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_ARB_multitfxturf
#dffinf GL_ARB_multitfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glAdtivfTfxturfARB (GLfnum);
GLAPI void APIENTRY glClifntAdtivfTfxturfARB (GLfnum);
GLAPI void APIENTRY glMultiTfxCoord1dARB (GLfnum, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord1dvARB (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord1fARB (GLfnum, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord1fvARB (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord1iARB (GLfnum, GLint);
GLAPI void APIENTRY glMultiTfxCoord1ivARB (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord1sARB (GLfnum, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord1svARB (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord2dARB (GLfnum, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord2dvARB (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord2fARB (GLfnum, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord2fvARB (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord2iARB (GLfnum, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord2ivARB (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord2sARB (GLfnum, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord2svARB (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord3dARB (GLfnum, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord3dvARB (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord3fARB (GLfnum, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord3fvARB (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord3iARB (GLfnum, GLint, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord3ivARB (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord3sARB (GLfnum, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord3svARB (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glMultiTfxCoord4dARB (GLfnum, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glMultiTfxCoord4dvARB (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glMultiTfxCoord4fARB (GLfnum, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glMultiTfxCoord4fvARB (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glMultiTfxCoord4iARB (GLfnum, GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glMultiTfxCoord4ivARB (GLfnum, donst GLint *);
GLAPI void APIENTRY glMultiTfxCoord4sARB (GLfnum, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glMultiTfxCoord4svARB (GLfnum, donst GLsiort *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLACTIVETEXTUREARBPROC) (GLfnum tfxturf);
typfdff void (APIENTRYP PFNGLCLIENTACTIVETEXTUREARBPROC) (GLfnum tfxturf);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1DARBPROC) (GLfnum tbrgft, GLdoublf s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1DVARBPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1FARBPROC) (GLfnum tbrgft, GLflobt s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1FVARBPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1IARBPROC) (GLfnum tbrgft, GLint s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1IVARBPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1SARBPROC) (GLfnum tbrgft, GLsiort s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1SVARBPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2DARBPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2DVARBPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2FARBPROC) (GLfnum tbrgft, GLflobt s, GLflobt t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2FVARBPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2IARBPROC) (GLfnum tbrgft, GLint s, GLint t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2IVARBPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2SARBPROC) (GLfnum tbrgft, GLsiort s, GLsiort t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2SVARBPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3DARBPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t, GLdoublf r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3DVARBPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3FARBPROC) (GLfnum tbrgft, GLflobt s, GLflobt t, GLflobt r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3FVARBPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3IARBPROC) (GLfnum tbrgft, GLint s, GLint t, GLint r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3IVARBPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3SARBPROC) (GLfnum tbrgft, GLsiort s, GLsiort t, GLsiort r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3SVARBPROC) (GLfnum tbrgft, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4DARBPROC) (GLfnum tbrgft, GLdoublf s, GLdoublf t, GLdoublf r, GLdoublf q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4DVARBPROC) (GLfnum tbrgft, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4FARBPROC) (GLfnum tbrgft, GLflobt s, GLflobt t, GLflobt r, GLflobt q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4FVARBPROC) (GLfnum tbrgft, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4IARBPROC) (GLfnum tbrgft, GLint s, GLint t, GLint r, GLint q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4IVARBPROC) (GLfnum tbrgft, donst GLint *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4SARBPROC) (GLfnum tbrgft, GLsiort s, GLsiort t, GLsiort r, GLsiort q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4SVARBPROC) (GLfnum tbrgft, donst GLsiort *v);
#fndif

#ifndff GL_ARB_trbnsposf_mbtrix
#dffinf GL_ARB_trbnsposf_mbtrix 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glLobdTrbnsposfMbtrixfARB (donst GLflobt *);
GLAPI void APIENTRY glLobdTrbnsposfMbtrixdARB (donst GLdoublf *);
GLAPI void APIENTRY glMultTrbnsposfMbtrixfARB (donst GLflobt *);
GLAPI void APIENTRY glMultTrbnsposfMbtrixdARB (donst GLdoublf *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLLOADTRANSPOSEMATRIXFARBPROC) (donst GLflobt *m);
typfdff void (APIENTRYP PFNGLLOADTRANSPOSEMATRIXDARBPROC) (donst GLdoublf *m);
typfdff void (APIENTRYP PFNGLMULTTRANSPOSEMATRIXFARBPROC) (donst GLflobt *m);
typfdff void (APIENTRYP PFNGLMULTTRANSPOSEMATRIXDARBPROC) (donst GLdoublf *m);
#fndif

#ifndff GL_ARB_multisbmplf
#dffinf GL_ARB_multisbmplf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSbmplfCovfrbgfARB (GLdlbmpf, GLboolfbn);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSAMPLECOVERAGEARBPROC) (GLdlbmpf vbluf, GLboolfbn invfrt);
#fndif

#ifndff GL_ARB_tfxturf_fnv_bdd
#dffinf GL_ARB_tfxturf_fnv_bdd 1
#fndif

#ifndff GL_ARB_tfxturf_dubf_mbp
#dffinf GL_ARB_tfxturf_dubf_mbp 1
#fndif

#ifndff GL_ARB_tfxturf_domprfssion
#dffinf GL_ARB_tfxturf_domprfssion 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glComprfssfdTfxImbgf3DARB (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxImbgf2DARB (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxImbgf1DARB (GLfnum, GLint, GLfnum, GLsizfi, GLint, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf3DARB (GLfnum, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf2DARB (GLfnum, GLint, GLint, GLint, GLsizfi, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glComprfssfdTfxSubImbgf1DARB (GLfnum, GLint, GLint, GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glGftComprfssfdTfxImbgfARB (GLfnum, GLint, GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE3DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE2DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXIMAGE1DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLint bordfr, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE3DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE2DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOMPRESSEDTEXSUBIMAGE1DARBPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLsizfi widti, GLfnum formbt, GLsizfi imbgfSizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLGETCOMPRESSEDTEXIMAGEARBPROC) (GLfnum tbrgft, GLint lfvfl, GLvoid *img);
#fndif

#ifndff GL_ARB_tfxturf_bordfr_dlbmp
#dffinf GL_ARB_tfxturf_bordfr_dlbmp 1
#fndif

#ifndff GL_ARB_point_pbrbmftfrs
#dffinf GL_ARB_point_pbrbmftfrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPointPbrbmftfrfARB (GLfnum, GLflobt);
GLAPI void APIENTRY glPointPbrbmftfrfvARB (GLfnum, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFARBPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFVARBPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
#fndif

#ifndff GL_ARB_vfrtfx_blfnd
#dffinf GL_ARB_vfrtfx_blfnd 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glWfigitbvARB (GLint, donst GLbytf *);
GLAPI void APIENTRY glWfigitsvARB (GLint, donst GLsiort *);
GLAPI void APIENTRY glWfigitivARB (GLint, donst GLint *);
GLAPI void APIENTRY glWfigitfvARB (GLint, donst GLflobt *);
GLAPI void APIENTRY glWfigitdvARB (GLint, donst GLdoublf *);
GLAPI void APIENTRY glWfigitubvARB (GLint, donst GLubytf *);
GLAPI void APIENTRY glWfigitusvARB (GLint, donst GLusiort *);
GLAPI void APIENTRY glWfigituivARB (GLint, donst GLuint *);
GLAPI void APIENTRY glWfigitPointfrARB (GLint, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glVfrtfxBlfndARB (GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLWEIGHTBVARBPROC) (GLint sizf, donst GLbytf *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTSVARBPROC) (GLint sizf, donst GLsiort *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTIVARBPROC) (GLint sizf, donst GLint *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTFVARBPROC) (GLint sizf, donst GLflobt *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTDVARBPROC) (GLint sizf, donst GLdoublf *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTUBVARBPROC) (GLint sizf, donst GLubytf *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTUSVARBPROC) (GLint sizf, donst GLusiort *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTUIVARBPROC) (GLint sizf, donst GLuint *wfigits);
typfdff void (APIENTRYP PFNGLWEIGHTPOINTERARBPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLVERTEXBLENDARBPROC) (GLint dount);
#fndif

#ifndff GL_ARB_mbtrix_pblfttf
#dffinf GL_ARB_mbtrix_pblfttf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glCurrfntPblfttfMbtrixARB (GLint);
GLAPI void APIENTRY glMbtrixIndfxubvARB (GLint, donst GLubytf *);
GLAPI void APIENTRY glMbtrixIndfxusvARB (GLint, donst GLusiort *);
GLAPI void APIENTRY glMbtrixIndfxuivARB (GLint, donst GLuint *);
GLAPI void APIENTRY glMbtrixIndfxPointfrARB (GLint, GLfnum, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCURRENTPALETTEMATRIXARBPROC) (GLint indfx);
typfdff void (APIENTRYP PFNGLMATRIXINDEXUBVARBPROC) (GLint sizf, donst GLubytf *indidfs);
typfdff void (APIENTRYP PFNGLMATRIXINDEXUSVARBPROC) (GLint sizf, donst GLusiort *indidfs);
typfdff void (APIENTRYP PFNGLMATRIXINDEXUIVARBPROC) (GLint sizf, donst GLuint *indidfs);
typfdff void (APIENTRYP PFNGLMATRIXINDEXPOINTERARBPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_ARB_tfxturf_fnv_dombinf
#dffinf GL_ARB_tfxturf_fnv_dombinf 1
#fndif

#ifndff GL_ARB_tfxturf_fnv_drossbbr
#dffinf GL_ARB_tfxturf_fnv_drossbbr 1
#fndif

#ifndff GL_ARB_tfxturf_fnv_dot3
#dffinf GL_ARB_tfxturf_fnv_dot3 1
#fndif

#ifndff GL_ARB_tfxturf_mirrorfd_rfpfbt
#dffinf GL_ARB_tfxturf_mirrorfd_rfpfbt 1
#fndif

#ifndff GL_ARB_dfpti_tfxturf
#dffinf GL_ARB_dfpti_tfxturf 1
#fndif

#ifndff GL_ARB_sibdow
#dffinf GL_ARB_sibdow 1
#fndif

#ifndff GL_ARB_sibdow_bmbifnt
#dffinf GL_ARB_sibdow_bmbifnt 1
#fndif

#ifndff GL_ARB_window_pos
#dffinf GL_ARB_window_pos 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glWindowPos2dARB (GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos2dvARB (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos2fARB (GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos2fvARB (donst GLflobt *);
GLAPI void APIENTRY glWindowPos2iARB (GLint, GLint);
GLAPI void APIENTRY glWindowPos2ivARB (donst GLint *);
GLAPI void APIENTRY glWindowPos2sARB (GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos2svARB (donst GLsiort *);
GLAPI void APIENTRY glWindowPos3dARB (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos3dvARB (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos3fARB (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos3fvARB (donst GLflobt *);
GLAPI void APIENTRY glWindowPos3iARB (GLint, GLint, GLint);
GLAPI void APIENTRY glWindowPos3ivARB (donst GLint *);
GLAPI void APIENTRY glWindowPos3sARB (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos3svARB (donst GLsiort *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLWINDOWPOS2DARBPROC) (GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2DVARBPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FARBPROC) (GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FVARBPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IARBPROC) (GLint x, GLint y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IVARBPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SARBPROC) (GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SVARBPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DARBPROC) (GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DVARBPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FARBPROC) (GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FVARBPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IARBPROC) (GLint x, GLint y, GLint z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IVARBPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SARBPROC) (GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SVARBPROC) (donst GLsiort *v);
#fndif

#ifndff GL_ARB_vfrtfx_progrbm
#dffinf GL_ARB_vfrtfx_progrbm 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxAttrib1dARB (GLuint, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib1dvARB (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib1fARB (GLuint, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib1fvARB (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib1sARB (GLuint, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib1svARB (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib2dARB (GLuint, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib2dvARB (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib2fARB (GLuint, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib2fvARB (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib2sARB (GLuint, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib2svARB (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib3dARB (GLuint, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib3dvARB (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib3fARB (GLuint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib3fvARB (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib3sARB (GLuint, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib3svARB (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4NbvARB (GLuint, donst GLbytf *);
GLAPI void APIENTRY glVfrtfxAttrib4NivARB (GLuint, donst GLint *);
GLAPI void APIENTRY glVfrtfxAttrib4NsvARB (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4NubARB (GLuint, GLubytf, GLubytf, GLubytf, GLubytf);
GLAPI void APIENTRY glVfrtfxAttrib4NubvARB (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVfrtfxAttrib4NuivARB (GLuint, donst GLuint *);
GLAPI void APIENTRY glVfrtfxAttrib4NusvARB (GLuint, donst GLusiort *);
GLAPI void APIENTRY glVfrtfxAttrib4bvARB (GLuint, donst GLbytf *);
GLAPI void APIENTRY glVfrtfxAttrib4dARB (GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib4dvARB (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib4fARB (GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib4fvARB (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib4ivARB (GLuint, donst GLint *);
GLAPI void APIENTRY glVfrtfxAttrib4sARB (GLuint, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib4svARB (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4ubvARB (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVfrtfxAttrib4uivARB (GLuint, donst GLuint *);
GLAPI void APIENTRY glVfrtfxAttrib4usvARB (GLuint, donst GLusiort *);
GLAPI void APIENTRY glVfrtfxAttribPointfrARB (GLuint, GLint, GLfnum, GLboolfbn, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glEnbblfVfrtfxAttribArrbyARB (GLuint);
GLAPI void APIENTRY glDisbblfVfrtfxAttribArrbyARB (GLuint);
GLAPI void APIENTRY glProgrbmStringARB (GLfnum, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glBindProgrbmARB (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfProgrbmsARB (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnProgrbmsARB (GLsizfi, GLuint *);
GLAPI void APIENTRY glProgrbmEnvPbrbmftfr4dARB (GLfnum, GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glProgrbmEnvPbrbmftfr4dvARB (GLfnum, GLuint, donst GLdoublf *);
GLAPI void APIENTRY glProgrbmEnvPbrbmftfr4fARB (GLfnum, GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glProgrbmEnvPbrbmftfr4fvARB (GLfnum, GLuint, donst GLflobt *);
GLAPI void APIENTRY glProgrbmLodblPbrbmftfr4dARB (GLfnum, GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glProgrbmLodblPbrbmftfr4dvARB (GLfnum, GLuint, donst GLdoublf *);
GLAPI void APIENTRY glProgrbmLodblPbrbmftfr4fARB (GLfnum, GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glProgrbmLodblPbrbmftfr4fvARB (GLfnum, GLuint, donst GLflobt *);
GLAPI void APIENTRY glGftProgrbmEnvPbrbmftfrdvARB (GLfnum, GLuint, GLdoublf *);
GLAPI void APIENTRY glGftProgrbmEnvPbrbmftfrfvARB (GLfnum, GLuint, GLflobt *);
GLAPI void APIENTRY glGftProgrbmLodblPbrbmftfrdvARB (GLfnum, GLuint, GLdoublf *);
GLAPI void APIENTRY glGftProgrbmLodblPbrbmftfrfvARB (GLfnum, GLuint, GLflobt *);
GLAPI void APIENTRY glGftProgrbmivARB (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftProgrbmStringARB (GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftVfrtfxAttribdvARB (GLuint, GLfnum, GLdoublf *);
GLAPI void APIENTRY glGftVfrtfxAttribfvARB (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVfrtfxAttribivARB (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftVfrtfxAttribPointfrvARB (GLuint, GLfnum, GLvoid* *);
GLAPI GLboolfbn APIENTRY glIsProgrbmARB (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DARBPROC) (GLuint indfx, GLdoublf x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DVARBPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FARBPROC) (GLuint indfx, GLflobt x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FVARBPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SARBPROC) (GLuint indfx, GLsiort x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SVARBPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DARBPROC) (GLuint indfx, GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DVARBPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FARBPROC) (GLuint indfx, GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FVARBPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SARBPROC) (GLuint indfx, GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SVARBPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DARBPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DVARBPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FARBPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FVARBPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SARBPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SVARBPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NBVARBPROC) (GLuint indfx, donst GLbytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NIVARBPROC) (GLuint indfx, donst GLint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NSVARBPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUBARBPROC) (GLuint indfx, GLubytf x, GLubytf y, GLubytf z, GLubytf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUBVARBPROC) (GLuint indfx, donst GLubytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUIVARBPROC) (GLuint indfx, donst GLuint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4NUSVARBPROC) (GLuint indfx, donst GLusiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4BVARBPROC) (GLuint indfx, donst GLbytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DARBPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DVARBPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FARBPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FVARBPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4IVARBPROC) (GLuint indfx, donst GLint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SARBPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z, GLsiort w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SVARBPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UBVARBPROC) (GLuint indfx, donst GLubytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UIVARBPROC) (GLuint indfx, donst GLuint *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4USVARBPROC) (GLuint indfx, donst GLusiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBPOINTERARBPROC) (GLuint indfx, GLint sizf, GLfnum typf, GLboolfbn normblizfd, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLENABLEVERTEXATTRIBARRAYARBPROC) (GLuint indfx);
typfdff void (APIENTRYP PFNGLDISABLEVERTEXATTRIBARRAYARBPROC) (GLuint indfx);
typfdff void (APIENTRYP PFNGLPROGRAMSTRINGARBPROC) (GLfnum tbrgft, GLfnum formbt, GLsizfi lfn, donst GLvoid *string);
typfdff void (APIENTRYP PFNGLBINDPROGRAMARBPROC) (GLfnum tbrgft, GLuint progrbm);
typfdff void (APIENTRYP PFNGLDELETEPROGRAMSARBPROC) (GLsizfi n, donst GLuint *progrbms);
typfdff void (APIENTRYP PFNGLGENPROGRAMSARBPROC) (GLsizfi n, GLuint *progrbms);
typfdff void (APIENTRYP PFNGLPROGRAMENVPARAMETER4DARBPROC) (GLfnum tbrgft, GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLPROGRAMENVPARAMETER4DVARBPROC) (GLfnum tbrgft, GLuint indfx, donst GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLPROGRAMENVPARAMETER4FARBPROC) (GLfnum tbrgft, GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLPROGRAMENVPARAMETER4FVARBPROC) (GLfnum tbrgft, GLuint indfx, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLPROGRAMLOCALPARAMETER4DARBPROC) (GLfnum tbrgft, GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLPROGRAMLOCALPARAMETER4DVARBPROC) (GLfnum tbrgft, GLuint indfx, donst GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLPROGRAMLOCALPARAMETER4FARBPROC) (GLfnum tbrgft, GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLPROGRAMLOCALPARAMETER4FVARBPROC) (GLfnum tbrgft, GLuint indfx, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMENVPARAMETERDVARBPROC) (GLfnum tbrgft, GLuint indfx, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMENVPARAMETERFVARBPROC) (GLfnum tbrgft, GLuint indfx, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMLOCALPARAMETERDVARBPROC) (GLfnum tbrgft, GLuint indfx, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMLOCALPARAMETERFVARBPROC) (GLfnum tbrgft, GLuint indfx, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMIVARBPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMSTRINGARBPROC) (GLfnum tbrgft, GLfnum pnbmf, GLvoid *string);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBDVARBPROC) (GLuint indfx, GLfnum pnbmf, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBFVARBPROC) (GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBIVARBPROC) (GLuint indfx, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBPOINTERVARBPROC) (GLuint indfx, GLfnum pnbmf, GLvoid* *pointfr);
typfdff GLboolfbn (APIENTRYP PFNGLISPROGRAMARBPROC) (GLuint progrbm);
#fndif

#ifndff GL_ARB_frbgmfnt_progrbm
#dffinf GL_ARB_frbgmfnt_progrbm 1
/* All ARB_frbgmfnt_progrbm fntry points brf sibrfd witi ARB_vfrtfx_progrbm. */
#fndif

#ifndff GL_ARB_vfrtfx_bufffr_objfdt
#dffinf GL_ARB_vfrtfx_bufffr_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBindBufffrARB (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfBufffrsARB (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnBufffrsARB (GLsizfi, GLuint *);
GLAPI GLboolfbn APIENTRY glIsBufffrARB (GLuint);
GLAPI void APIENTRY glBufffrDbtbARB (GLfnum, GLsizfiptrARB, donst GLvoid *, GLfnum);
GLAPI void APIENTRY glBufffrSubDbtbARB (GLfnum, GLintptrARB, GLsizfiptrARB, donst GLvoid *);
GLAPI void APIENTRY glGftBufffrSubDbtbARB (GLfnum, GLintptrARB, GLsizfiptrARB, GLvoid *);
GLAPI GLvoid* APIENTRY glMbpBufffrARB (GLfnum, GLfnum);
GLAPI GLboolfbn APIENTRY glUnmbpBufffrARB (GLfnum);
GLAPI void APIENTRY glGftBufffrPbrbmftfrivARB (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftBufffrPointfrvARB (GLfnum, GLfnum, GLvoid* *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBINDBUFFERARBPROC) (GLfnum tbrgft, GLuint bufffr);
typfdff void (APIENTRYP PFNGLDELETEBUFFERSARBPROC) (GLsizfi n, donst GLuint *bufffrs);
typfdff void (APIENTRYP PFNGLGENBUFFERSARBPROC) (GLsizfi n, GLuint *bufffrs);
typfdff GLboolfbn (APIENTRYP PFNGLISBUFFERARBPROC) (GLuint bufffr);
typfdff void (APIENTRYP PFNGLBUFFERDATAARBPROC) (GLfnum tbrgft, GLsizfiptrARB sizf, donst GLvoid *dbtb, GLfnum usbgf);
typfdff void (APIENTRYP PFNGLBUFFERSUBDATAARBPROC) (GLfnum tbrgft, GLintptrARB offsft, GLsizfiptrARB sizf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLGETBUFFERSUBDATAARBPROC) (GLfnum tbrgft, GLintptrARB offsft, GLsizfiptrARB sizf, GLvoid *dbtb);
typfdff GLvoid* (APIENTRYP PFNGLMAPBUFFERARBPROC) (GLfnum tbrgft, GLfnum bddfss);
typfdff GLboolfbn (APIENTRYP PFNGLUNMAPBUFFERARBPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLGETBUFFERPARAMETERIVARBPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETBUFFERPOINTERVARBPROC) (GLfnum tbrgft, GLfnum pnbmf, GLvoid* *pbrbms);
#fndif

#ifndff GL_ARB_oddlusion_qufry
#dffinf GL_ARB_oddlusion_qufry 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGfnQufrifsARB (GLsizfi, GLuint *);
GLAPI void APIENTRY glDflftfQufrifsARB (GLsizfi, donst GLuint *);
GLAPI GLboolfbn APIENTRY glIsQufryARB (GLuint);
GLAPI void APIENTRY glBfginQufryARB (GLfnum, GLuint);
GLAPI void APIENTRY glEndQufryARB (GLfnum);
GLAPI void APIENTRY glGftQufryivARB (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftQufryObjfdtivARB (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftQufryObjfdtuivARB (GLuint, GLfnum, GLuint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGENQUERIESARBPROC) (GLsizfi n, GLuint *ids);
typfdff void (APIENTRYP PFNGLDELETEQUERIESARBPROC) (GLsizfi n, donst GLuint *ids);
typfdff GLboolfbn (APIENTRYP PFNGLISQUERYARBPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLBEGINQUERYARBPROC) (GLfnum tbrgft, GLuint id);
typfdff void (APIENTRYP PFNGLENDQUERYARBPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLGETQUERYIVARBPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETQUERYOBJECTIVARBPROC) (GLuint id, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETQUERYOBJECTUIVARBPROC) (GLuint id, GLfnum pnbmf, GLuint *pbrbms);
#fndif

#ifndff GL_ARB_sibdfr_objfdts
#dffinf GL_ARB_sibdfr_objfdts 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDflftfObjfdtARB (GLibndlfARB);
GLAPI GLibndlfARB APIENTRY glGftHbndlfARB (GLfnum);
GLAPI void APIENTRY glDftbdiObjfdtARB (GLibndlfARB, GLibndlfARB);
GLAPI GLibndlfARB APIENTRY glCrfbtfSibdfrObjfdtARB (GLfnum);
GLAPI void APIENTRY glSibdfrSourdfARB (GLibndlfARB, GLsizfi, donst GLdibrARB* *, donst GLint *);
GLAPI void APIENTRY glCompilfSibdfrARB (GLibndlfARB);
GLAPI GLibndlfARB APIENTRY glCrfbtfProgrbmObjfdtARB (void);
GLAPI void APIENTRY glAttbdiObjfdtARB (GLibndlfARB, GLibndlfARB);
GLAPI void APIENTRY glLinkProgrbmARB (GLibndlfARB);
GLAPI void APIENTRY glUsfProgrbmObjfdtARB (GLibndlfARB);
GLAPI void APIENTRY glVblidbtfProgrbmARB (GLibndlfARB);
GLAPI void APIENTRY glUniform1fARB (GLint, GLflobt);
GLAPI void APIENTRY glUniform2fARB (GLint, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform3fARB (GLint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform4fARB (GLint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glUniform1iARB (GLint, GLint);
GLAPI void APIENTRY glUniform2iARB (GLint, GLint, GLint);
GLAPI void APIENTRY glUniform3iARB (GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glUniform4iARB (GLint, GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glUniform1fvARB (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform2fvARB (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform3fvARB (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform4fvARB (GLint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glUniform1ivARB (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform2ivARB (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform3ivARB (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniform4ivARB (GLint, GLsizfi, donst GLint *);
GLAPI void APIENTRY glUniformMbtrix2fvARB (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glUniformMbtrix3fvARB (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glUniformMbtrix4fvARB (GLint, GLsizfi, GLboolfbn, donst GLflobt *);
GLAPI void APIENTRY glGftObjfdtPbrbmftfrfvARB (GLibndlfARB, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftObjfdtPbrbmftfrivARB (GLibndlfARB, GLfnum, GLint *);
GLAPI void APIENTRY glGftInfoLogARB (GLibndlfARB, GLsizfi, GLsizfi *, GLdibrARB *);
GLAPI void APIENTRY glGftAttbdifdObjfdtsARB (GLibndlfARB, GLsizfi, GLsizfi *, GLibndlfARB *);
GLAPI GLint APIENTRY glGftUniformLodbtionARB (GLibndlfARB, donst GLdibrARB *);
GLAPI void APIENTRY glGftAdtivfUniformARB (GLibndlfARB, GLuint, GLsizfi, GLsizfi *, GLint *, GLfnum *, GLdibrARB *);
GLAPI void APIENTRY glGftUniformfvARB (GLibndlfARB, GLint, GLflobt *);
GLAPI void APIENTRY glGftUniformivARB (GLibndlfARB, GLint, GLint *);
GLAPI void APIENTRY glGftSibdfrSourdfARB (GLibndlfARB, GLsizfi, GLsizfi *, GLdibrARB *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDELETEOBJECTARBPROC) (GLibndlfARB obj);
typfdff GLibndlfARB (APIENTRYP PFNGLGETHANDLEARBPROC) (GLfnum pnbmf);
typfdff void (APIENTRYP PFNGLDETACHOBJECTARBPROC) (GLibndlfARB dontbinfrObj, GLibndlfARB bttbdifdObj);
typfdff GLibndlfARB (APIENTRYP PFNGLCREATESHADEROBJECTARBPROC) (GLfnum sibdfrTypf);
typfdff void (APIENTRYP PFNGLSHADERSOURCEARBPROC) (GLibndlfARB sibdfrObj, GLsizfi dount, donst GLdibrARB* *string, donst GLint *lfngti);
typfdff void (APIENTRYP PFNGLCOMPILESHADERARBPROC) (GLibndlfARB sibdfrObj);
typfdff GLibndlfARB (APIENTRYP PFNGLCREATEPROGRAMOBJECTARBPROC) (void);
typfdff void (APIENTRYP PFNGLATTACHOBJECTARBPROC) (GLibndlfARB dontbinfrObj, GLibndlfARB obj);
typfdff void (APIENTRYP PFNGLLINKPROGRAMARBPROC) (GLibndlfARB progrbmObj);
typfdff void (APIENTRYP PFNGLUSEPROGRAMOBJECTARBPROC) (GLibndlfARB progrbmObj);
typfdff void (APIENTRYP PFNGLVALIDATEPROGRAMARBPROC) (GLibndlfARB progrbmObj);
typfdff void (APIENTRYP PFNGLUNIFORM1FARBPROC) (GLint lodbtion, GLflobt v0);
typfdff void (APIENTRYP PFNGLUNIFORM2FARBPROC) (GLint lodbtion, GLflobt v0, GLflobt v1);
typfdff void (APIENTRYP PFNGLUNIFORM3FARBPROC) (GLint lodbtion, GLflobt v0, GLflobt v1, GLflobt v2);
typfdff void (APIENTRYP PFNGLUNIFORM4FARBPROC) (GLint lodbtion, GLflobt v0, GLflobt v1, GLflobt v2, GLflobt v3);
typfdff void (APIENTRYP PFNGLUNIFORM1IARBPROC) (GLint lodbtion, GLint v0);
typfdff void (APIENTRYP PFNGLUNIFORM2IARBPROC) (GLint lodbtion, GLint v0, GLint v1);
typfdff void (APIENTRYP PFNGLUNIFORM3IARBPROC) (GLint lodbtion, GLint v0, GLint v1, GLint v2);
typfdff void (APIENTRYP PFNGLUNIFORM4IARBPROC) (GLint lodbtion, GLint v0, GLint v1, GLint v2, GLint v3);
typfdff void (APIENTRYP PFNGLUNIFORM1FVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM2FVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM3FVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM4FVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM1IVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM2IVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM3IVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORM4IVARBPROC) (GLint lodbtion, GLsizfi dount, donst GLint *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX2FVARBPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX3FVARBPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLUNIFORMMATRIX4FVARBPROC) (GLint lodbtion, GLsizfi dount, GLboolfbn trbnsposf, donst GLflobt *vbluf);
typfdff void (APIENTRYP PFNGLGETOBJECTPARAMETERFVARBPROC) (GLibndlfARB obj, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETOBJECTPARAMETERIVARBPROC) (GLibndlfARB obj, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETINFOLOGARBPROC) (GLibndlfARB obj, GLsizfi mbxLfngti, GLsizfi *lfngti, GLdibrARB *infoLog);
typfdff void (APIENTRYP PFNGLGETATTACHEDOBJECTSARBPROC) (GLibndlfARB dontbinfrObj, GLsizfi mbxCount, GLsizfi *dount, GLibndlfARB *obj);
typfdff GLint (APIENTRYP PFNGLGETUNIFORMLOCATIONARBPROC) (GLibndlfARB progrbmObj, donst GLdibrARB *nbmf);
typfdff void (APIENTRYP PFNGLGETACTIVEUNIFORMARBPROC) (GLibndlfARB progrbmObj, GLuint indfx, GLsizfi mbxLfngti, GLsizfi *lfngti, GLint *sizf, GLfnum *typf, GLdibrARB *nbmf);
typfdff void (APIENTRYP PFNGLGETUNIFORMFVARBPROC) (GLibndlfARB progrbmObj, GLint lodbtion, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETUNIFORMIVARBPROC) (GLibndlfARB progrbmObj, GLint lodbtion, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETSHADERSOURCEARBPROC) (GLibndlfARB obj, GLsizfi mbxLfngti, GLsizfi *lfngti, GLdibrARB *sourdf);
#fndif

#ifndff GL_ARB_vfrtfx_sibdfr
#dffinf GL_ARB_vfrtfx_sibdfr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBindAttribLodbtionARB (GLibndlfARB, GLuint, donst GLdibrARB *);
GLAPI void APIENTRY glGftAdtivfAttribARB (GLibndlfARB, GLuint, GLsizfi, GLsizfi *, GLint *, GLfnum *, GLdibrARB *);
GLAPI GLint APIENTRY glGftAttribLodbtionARB (GLibndlfARB, donst GLdibrARB *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBINDATTRIBLOCATIONARBPROC) (GLibndlfARB progrbmObj, GLuint indfx, donst GLdibrARB *nbmf);
typfdff void (APIENTRYP PFNGLGETACTIVEATTRIBARBPROC) (GLibndlfARB progrbmObj, GLuint indfx, GLsizfi mbxLfngti, GLsizfi *lfngti, GLint *sizf, GLfnum *typf, GLdibrARB *nbmf);
typfdff GLint (APIENTRYP PFNGLGETATTRIBLOCATIONARBPROC) (GLibndlfARB progrbmObj, donst GLdibrARB *nbmf);
#fndif

#ifndff GL_ARB_frbgmfnt_sibdfr
#dffinf GL_ARB_frbgmfnt_sibdfr 1
#fndif

#ifndff GL_ARB_sibding_lbngubgf_100
#dffinf GL_ARB_sibding_lbngubgf_100 1
#fndif

#ifndff GL_ARB_tfxturf_non_powfr_of_two
#dffinf GL_ARB_tfxturf_non_powfr_of_two 1
#fndif

#ifndff GL_ARB_point_spritf
#dffinf GL_ARB_point_spritf 1
#fndif

#ifndff GL_ARB_frbgmfnt_progrbm_sibdow
#dffinf GL_ARB_frbgmfnt_progrbm_sibdow 1
#fndif

#ifndff GL_ARB_drbw_bufffrs
#dffinf GL_ARB_drbw_bufffrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDrbwBufffrsARB (GLsizfi, donst GLfnum *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDRAWBUFFERSARBPROC) (GLsizfi n, donst GLfnum *bufs);
#fndif

#ifndff GL_ARB_tfxturf_rfdtbnglf
#dffinf GL_ARB_tfxturf_rfdtbnglf 1
#fndif

#ifndff GL_ARB_dolor_bufffr_flobt
#dffinf GL_ARB_dolor_bufffr_flobt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glClbmpColorARB (GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCLAMPCOLORARBPROC) (GLfnum tbrgft, GLfnum dlbmp);
#fndif

#ifndff GL_ARB_iblf_flobt_pixfl
#dffinf GL_ARB_iblf_flobt_pixfl 1
#fndif

#ifndff GL_ARB_tfxturf_flobt
#dffinf GL_ARB_tfxturf_flobt 1
#fndif

#ifndff GL_ARB_pixfl_bufffr_objfdt
#dffinf GL_ARB_pixfl_bufffr_objfdt 1
#fndif

#ifndff GL_EXT_bbgr
#dffinf GL_EXT_bbgr 1
#fndif

#ifndff GL_EXT_blfnd_dolor
#dffinf GL_EXT_blfnd_dolor 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndColorEXT (GLdlbmpf, GLdlbmpf, GLdlbmpf, GLdlbmpf);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDCOLOREXTPROC) (GLdlbmpf rfd, GLdlbmpf grffn, GLdlbmpf bluf, GLdlbmpf blpib);
#fndif

#ifndff GL_EXT_polygon_offsft
#dffinf GL_EXT_polygon_offsft 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPolygonOffsftEXT (GLflobt, GLflobt);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPOLYGONOFFSETEXTPROC) (GLflobt fbdtor, GLflobt bibs);
#fndif

#ifndff GL_EXT_tfxturf
#dffinf GL_EXT_tfxturf 1
#fndif

#ifndff GL_EXT_tfxturf3D
#dffinf GL_EXT_tfxturf3D 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxImbgf3DEXT (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLsizfi, GLint, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glTfxSubImbgf3DEXT (GLfnum, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXIMAGE3DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLint bordfr, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
typfdff void (APIENTRYP PFNGLTEXSUBIMAGE3DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
#fndif

#ifndff GL_SGIS_tfxturf_filtfr4
#dffinf GL_SGIS_tfxturf_filtfr4 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGftTfxFiltfrFundSGIS (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glTfxFiltfrFundSGIS (GLfnum, GLfnum, GLsizfi, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGETTEXFILTERFUNCSGISPROC) (GLfnum tbrgft, GLfnum filtfr, GLflobt *wfigits);
typfdff void (APIENTRYP PFNGLTEXFILTERFUNCSGISPROC) (GLfnum tbrgft, GLfnum filtfr, GLsizfi n, donst GLflobt *wfigits);
#fndif

#ifndff GL_EXT_subtfxturf
#dffinf GL_EXT_subtfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxSubImbgf1DEXT (GLfnum, GLint, GLint, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glTfxSubImbgf2DEXT (GLfnum, GLint, GLint, GLint, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXSUBIMAGE1DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
typfdff void (APIENTRYP PFNGLTEXSUBIMAGE2DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
#fndif

#ifndff GL_EXT_dopy_tfxturf
#dffinf GL_EXT_dopy_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glCopyTfxImbgf1DEXT (GLfnum, GLint, GLfnum, GLint, GLint, GLsizfi, GLint);
GLAPI void APIENTRY glCopyTfxImbgf2DEXT (GLfnum, GLint, GLfnum, GLint, GLint, GLsizfi, GLsizfi, GLint);
GLAPI void APIENTRY glCopyTfxSubImbgf1DEXT (GLfnum, GLint, GLint, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glCopyTfxSubImbgf2DEXT (GLfnum, GLint, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi);
GLAPI void APIENTRY glCopyTfxSubImbgf3DEXT (GLfnum, GLint, GLint, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOPYTEXIMAGE1DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti, GLint bordfr);
typfdff void (APIENTRYP PFNGLCOPYTEXIMAGE2DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit, GLint bordfr);
typfdff void (APIENTRYP PFNGLCOPYTEXSUBIMAGE1DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLCOPYTEXSUBIMAGE2DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit);
typfdff void (APIENTRYP PFNGLCOPYTEXSUBIMAGE3DEXTPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit);
#fndif

#ifndff GL_EXT_iistogrbm
#dffinf GL_EXT_iistogrbm 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGftHistogrbmEXT (GLfnum, GLboolfbn, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftHistogrbmPbrbmftfrfvEXT (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftHistogrbmPbrbmftfrivEXT (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftMinmbxEXT (GLfnum, GLboolfbn, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftMinmbxPbrbmftfrfvEXT (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftMinmbxPbrbmftfrivEXT (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glHistogrbmEXT (GLfnum, GLsizfi, GLfnum, GLboolfbn);
GLAPI void APIENTRY glMinmbxEXT (GLfnum, GLfnum, GLboolfbn);
GLAPI void APIENTRY glRfsftHistogrbmEXT (GLfnum);
GLAPI void APIENTRY glRfsftMinmbxEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGETHISTOGRAMEXTPROC) (GLfnum tbrgft, GLboolfbn rfsft, GLfnum formbt, GLfnum typf, GLvoid *vblufs);
typfdff void (APIENTRYP PFNGLGETHISTOGRAMPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETHISTOGRAMPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETMINMAXEXTPROC) (GLfnum tbrgft, GLboolfbn rfsft, GLfnum formbt, GLfnum typf, GLvoid *vblufs);
typfdff void (APIENTRYP PFNGLGETMINMAXPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETMINMAXPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLHISTOGRAMEXTPROC) (GLfnum tbrgft, GLsizfi widti, GLfnum intfrnblformbt, GLboolfbn sink);
typfdff void (APIENTRYP PFNGLMINMAXEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLboolfbn sink);
typfdff void (APIENTRYP PFNGLRESETHISTOGRAMEXTPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLRESETMINMAXEXTPROC) (GLfnum tbrgft);
#fndif

#ifndff GL_EXT_donvolution
#dffinf GL_EXT_donvolution 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glConvolutionFiltfr1DEXT (GLfnum, GLfnum, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glConvolutionFiltfr2DEXT (GLfnum, GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glConvolutionPbrbmftfrfEXT (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glConvolutionPbrbmftfrfvEXT (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glConvolutionPbrbmftfriEXT (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glConvolutionPbrbmftfrivEXT (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glCopyConvolutionFiltfr1DEXT (GLfnum, GLfnum, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glCopyConvolutionFiltfr2DEXT (GLfnum, GLfnum, GLint, GLint, GLsizfi, GLsizfi);
GLAPI void APIENTRY glGftConvolutionFiltfrEXT (GLfnum, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftConvolutionPbrbmftfrfvEXT (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftConvolutionPbrbmftfrivEXT (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftSfpbrbblfFiltfrEXT (GLfnum, GLfnum, GLfnum, GLvoid *, GLvoid *, GLvoid *);
GLAPI void APIENTRY glSfpbrbblfFiltfr2DEXT (GLfnum, GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCONVOLUTIONFILTER1DEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLCONVOLUTIONFILTER2DEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLfnum typf, donst GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERFEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERIEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint pbrbms);
typfdff void (APIENTRYP PFNGLCONVOLUTIONPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOPYCONVOLUTIONFILTER1DEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLCOPYCONVOLUTIONFILTER2DEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti, GLsizfi ifigit);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONFILTEREXTPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *imbgf);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCONVOLUTIONPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETSEPARABLEFILTEREXTPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *row, GLvoid *dolumn, GLvoid *spbn);
typfdff void (APIENTRYP PFNGLSEPARABLEFILTER2DEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLfnum formbt, GLfnum typf, donst GLvoid *row, donst GLvoid *dolumn);
#fndif

#ifndff GL_EXT_dolor_mbtrix
#dffinf GL_EXT_dolor_mbtrix 1
#fndif

#ifndff GL_SGI_dolor_tbblf
#dffinf GL_SGI_dolor_tbblf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glColorTbblfSGI (GLfnum, GLfnum, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glColorTbblfPbrbmftfrfvSGI (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glColorTbblfPbrbmftfrivSGI (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glCopyColorTbblfSGI (GLfnum, GLfnum, GLint, GLint, GLsizfi);
GLAPI void APIENTRY glGftColorTbblfSGI (GLfnum, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfrfvSGI (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfrivSGI (GLfnum, GLfnum, GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOLORTABLESGIPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *tbblf);
typfdff void (APIENTRYP PFNGLCOLORTABLEPARAMETERFVSGIPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLCOLORTABLEPARAMETERIVSGIPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOPYCOLORTABLESGIPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLint x, GLint y, GLsizfi widti);
typfdff void (APIENTRYP PFNGLGETCOLORTABLESGIPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *tbblf);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERFVSGIPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERIVSGIPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
#fndif

#ifndff GL_SGIX_pixfl_tfxturf
#dffinf GL_SGIX_pixfl_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPixflTfxGfnSGIX (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPIXELTEXGENSGIXPROC) (GLfnum modf);
#fndif

#ifndff GL_SGIS_pixfl_tfxturf
#dffinf GL_SGIS_pixfl_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPixflTfxGfnPbrbmftfriSGIS (GLfnum, GLint);
GLAPI void APIENTRY glPixflTfxGfnPbrbmftfrivSGIS (GLfnum, donst GLint *);
GLAPI void APIENTRY glPixflTfxGfnPbrbmftfrfSGIS (GLfnum, GLflobt);
GLAPI void APIENTRY glPixflTfxGfnPbrbmftfrfvSGIS (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glGftPixflTfxGfnPbrbmftfrivSGIS (GLfnum, GLint *);
GLAPI void APIENTRY glGftPixflTfxGfnPbrbmftfrfvSGIS (GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPIXELTEXGENPARAMETERISGISPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLPIXELTEXGENPARAMETERIVSGISPROC) (GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLPIXELTEXGENPARAMETERFSGISPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPIXELTEXGENPARAMETERFVSGISPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPIXELTEXGENPARAMETERIVSGISPROC) (GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETPIXELTEXGENPARAMETERFVSGISPROC) (GLfnum pnbmf, GLflobt *pbrbms);
#fndif

#ifndff GL_SGIS_tfxturf4D
#dffinf GL_SGIS_tfxturf4D 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxImbgf4DSGIS (GLfnum, GLint, GLfnum, GLsizfi, GLsizfi, GLsizfi, GLsizfi, GLint, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glTfxSubImbgf4DSGIS (GLfnum, GLint, GLint, GLint, GLint, GLint, GLsizfi, GLsizfi, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXIMAGE4DSGISPROC) (GLfnum tbrgft, GLint lfvfl, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLsizfi sizf4d, GLint bordfr, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
typfdff void (APIENTRYP PFNGLTEXSUBIMAGE4DSGISPROC) (GLfnum tbrgft, GLint lfvfl, GLint xoffsft, GLint yoffsft, GLint zoffsft, GLint woffsft, GLsizfi widti, GLsizfi ifigit, GLsizfi dfpti, GLsizfi sizf4d, GLfnum formbt, GLfnum typf, donst GLvoid *pixfls);
#fndif

#ifndff GL_SGI_tfxturf_dolor_tbblf
#dffinf GL_SGI_tfxturf_dolor_tbblf 1
#fndif

#ifndff GL_EXT_dmykb
#dffinf GL_EXT_dmykb 1
#fndif

#ifndff GL_EXT_tfxturf_objfdt
#dffinf GL_EXT_tfxturf_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLboolfbn APIENTRY glArfTfxturfsRfsidfntEXT (GLsizfi, donst GLuint *, GLboolfbn *);
GLAPI void APIENTRY glBindTfxturfEXT (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfTfxturfsEXT (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnTfxturfsEXT (GLsizfi, GLuint *);
GLAPI GLboolfbn APIENTRY glIsTfxturfEXT (GLuint);
GLAPI void APIENTRY glPrioritizfTfxturfsEXT (GLsizfi, donst GLuint *, donst GLdlbmpf *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLboolfbn (APIENTRYP PFNGLARETEXTURESRESIDENTEXTPROC) (GLsizfi n, donst GLuint *tfxturfs, GLboolfbn *rfsidfndfs);
typfdff void (APIENTRYP PFNGLBINDTEXTUREEXTPROC) (GLfnum tbrgft, GLuint tfxturf);
typfdff void (APIENTRYP PFNGLDELETETEXTURESEXTPROC) (GLsizfi n, donst GLuint *tfxturfs);
typfdff void (APIENTRYP PFNGLGENTEXTURESEXTPROC) (GLsizfi n, GLuint *tfxturfs);
typfdff GLboolfbn (APIENTRYP PFNGLISTEXTUREEXTPROC) (GLuint tfxturf);
typfdff void (APIENTRYP PFNGLPRIORITIZETEXTURESEXTPROC) (GLsizfi n, donst GLuint *tfxturfs, donst GLdlbmpf *prioritifs);
#fndif

#ifndff GL_SGIS_dftbil_tfxturf
#dffinf GL_SGIS_dftbil_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDftbilTfxFundSGIS (GLfnum, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glGftDftbilTfxFundSGIS (GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDETAILTEXFUNCSGISPROC) (GLfnum tbrgft, GLsizfi n, donst GLflobt *points);
typfdff void (APIENTRYP PFNGLGETDETAILTEXFUNCSGISPROC) (GLfnum tbrgft, GLflobt *points);
#fndif

#ifndff GL_SGIS_sibrpfn_tfxturf
#dffinf GL_SGIS_sibrpfn_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSibrpfnTfxFundSGIS (GLfnum, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glGftSibrpfnTfxFundSGIS (GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSHARPENTEXFUNCSGISPROC) (GLfnum tbrgft, GLsizfi n, donst GLflobt *points);
typfdff void (APIENTRYP PFNGLGETSHARPENTEXFUNCSGISPROC) (GLfnum tbrgft, GLflobt *points);
#fndif

#ifndff GL_EXT_pbdkfd_pixfls
#dffinf GL_EXT_pbdkfd_pixfls 1
#fndif

#ifndff GL_SGIS_tfxturf_lod
#dffinf GL_SGIS_tfxturf_lod 1
#fndif

#ifndff GL_SGIS_multisbmplf
#dffinf GL_SGIS_multisbmplf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSbmplfMbskSGIS (GLdlbmpf, GLboolfbn);
GLAPI void APIENTRY glSbmplfPbttfrnSGIS (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSAMPLEMASKSGISPROC) (GLdlbmpf vbluf, GLboolfbn invfrt);
typfdff void (APIENTRYP PFNGLSAMPLEPATTERNSGISPROC) (GLfnum pbttfrn);
#fndif

#ifndff GL_EXT_rfsdblf_normbl
#dffinf GL_EXT_rfsdblf_normbl 1
#fndif

#ifndff GL_EXT_vfrtfx_brrby
#dffinf GL_EXT_vfrtfx_brrby 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glArrbyElfmfntEXT (GLint);
GLAPI void APIENTRY glColorPointfrEXT (GLint, GLfnum, GLsizfi, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glDrbwArrbysEXT (GLfnum, GLint, GLsizfi);
GLAPI void APIENTRY glEdgfFlbgPointfrEXT (GLsizfi, GLsizfi, donst GLboolfbn *);
GLAPI void APIENTRY glGftPointfrvEXT (GLfnum, GLvoid* *);
GLAPI void APIENTRY glIndfxPointfrEXT (GLfnum, GLsizfi, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glNormblPointfrEXT (GLfnum, GLsizfi, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glTfxCoordPointfrEXT (GLint, GLfnum, GLsizfi, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glVfrtfxPointfrEXT (GLint, GLfnum, GLsizfi, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLARRAYELEMENTEXTPROC) (GLint i);
typfdff void (APIENTRYP PFNGLCOLORPOINTEREXTPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, GLsizfi dount, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLDRAWARRAYSEXTPROC) (GLfnum modf, GLint first, GLsizfi dount);
typfdff void (APIENTRYP PFNGLEDGEFLAGPOINTEREXTPROC) (GLsizfi stridf, GLsizfi dount, donst GLboolfbn *pointfr);
typfdff void (APIENTRYP PFNGLGETPOINTERVEXTPROC) (GLfnum pnbmf, GLvoid* *pbrbms);
typfdff void (APIENTRYP PFNGLINDEXPOINTEREXTPROC) (GLfnum typf, GLsizfi stridf, GLsizfi dount, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLNORMALPOINTEREXTPROC) (GLfnum typf, GLsizfi stridf, GLsizfi dount, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLTEXCOORDPOINTEREXTPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, GLsizfi dount, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLVERTEXPOINTEREXTPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, GLsizfi dount, donst GLvoid *pointfr);
#fndif

#ifndff GL_EXT_misd_bttributf
#dffinf GL_EXT_misd_bttributf 1
#fndif

#ifndff GL_SGIS_gfnfrbtf_mipmbp
#dffinf GL_SGIS_gfnfrbtf_mipmbp 1
#fndif

#ifndff GL_SGIX_dlipmbp
#dffinf GL_SGIX_dlipmbp 1
#fndif

#ifndff GL_SGIX_sibdow
#dffinf GL_SGIX_sibdow 1
#fndif

#ifndff GL_SGIS_tfxturf_fdgf_dlbmp
#dffinf GL_SGIS_tfxturf_fdgf_dlbmp 1
#fndif

#ifndff GL_SGIS_tfxturf_bordfr_dlbmp
#dffinf GL_SGIS_tfxturf_bordfr_dlbmp 1
#fndif

#ifndff GL_EXT_blfnd_minmbx
#dffinf GL_EXT_blfnd_minmbx 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndEqubtionEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDEQUATIONEXTPROC) (GLfnum modf);
#fndif

#ifndff GL_EXT_blfnd_subtrbdt
#dffinf GL_EXT_blfnd_subtrbdt 1
#fndif

#ifndff GL_EXT_blfnd_logid_op
#dffinf GL_EXT_blfnd_logid_op 1
#fndif

#ifndff GL_SGIX_intfrlbdf
#dffinf GL_SGIX_intfrlbdf 1
#fndif

#ifndff GL_SGIX_pixfl_tilfs
#dffinf GL_SGIX_pixfl_tilfs 1
#fndif

#ifndff GL_SGIX_tfxturf_sflfdt
#dffinf GL_SGIX_tfxturf_sflfdt 1
#fndif

#ifndff GL_SGIX_spritf
#dffinf GL_SGIX_spritf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSpritfPbrbmftfrfSGIX (GLfnum, GLflobt);
GLAPI void APIENTRY glSpritfPbrbmftfrfvSGIX (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glSpritfPbrbmftfriSGIX (GLfnum, GLint);
GLAPI void APIENTRY glSpritfPbrbmftfrivSGIX (GLfnum, donst GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSPRITEPARAMETERFSGIXPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLSPRITEPARAMETERFVSGIXPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLSPRITEPARAMETERISGIXPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLSPRITEPARAMETERIVSGIXPROC) (GLfnum pnbmf, donst GLint *pbrbms);
#fndif

#ifndff GL_SGIX_tfxturf_multi_bufffr
#dffinf GL_SGIX_tfxturf_multi_bufffr 1
#fndif

#ifndff GL_EXT_point_pbrbmftfrs
#dffinf GL_EXT_point_pbrbmftfrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPointPbrbmftfrfEXT (GLfnum, GLflobt);
GLAPI void APIENTRY glPointPbrbmftfrfvEXT (GLfnum, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFEXTPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFVEXTPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
#fndif

#ifndff GL_SGIS_point_pbrbmftfrs
#dffinf GL_SGIS_point_pbrbmftfrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPointPbrbmftfrfSGIS (GLfnum, GLflobt);
GLAPI void APIENTRY glPointPbrbmftfrfvSGIS (GLfnum, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFSGISPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERFVSGISPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
#fndif

#ifndff GL_SGIX_instrumfnts
#dffinf GL_SGIX_instrumfnts 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLint APIENTRY glGftInstrumfntsSGIX (void);
GLAPI void APIENTRY glInstrumfntsBufffrSGIX (GLsizfi, GLint *);
GLAPI GLint APIENTRY glPollInstrumfntsSGIX (GLint *);
GLAPI void APIENTRY glRfbdInstrumfntsSGIX (GLint);
GLAPI void APIENTRY glStbrtInstrumfntsSGIX (void);
GLAPI void APIENTRY glStopInstrumfntsSGIX (GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLint (APIENTRYP PFNGLGETINSTRUMENTSSGIXPROC) (void);
typfdff void (APIENTRYP PFNGLINSTRUMENTSBUFFERSGIXPROC) (GLsizfi sizf, GLint *bufffr);
typfdff GLint (APIENTRYP PFNGLPOLLINSTRUMENTSSGIXPROC) (GLint *mbrkfr_p);
typfdff void (APIENTRYP PFNGLREADINSTRUMENTSSGIXPROC) (GLint mbrkfr);
typfdff void (APIENTRYP PFNGLSTARTINSTRUMENTSSGIXPROC) (void);
typfdff void (APIENTRYP PFNGLSTOPINSTRUMENTSSGIXPROC) (GLint mbrkfr);
#fndif

#ifndff GL_SGIX_tfxturf_sdblf_bibs
#dffinf GL_SGIX_tfxturf_sdblf_bibs 1
#fndif

#ifndff GL_SGIX_frbmfzoom
#dffinf GL_SGIX_frbmfzoom 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFrbmfZoomSGIX (GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFRAMEZOOMSGIXPROC) (GLint fbdtor);
#fndif

#ifndff GL_SGIX_tbg_sbmplf_bufffr
#dffinf GL_SGIX_tbg_sbmplf_bufffr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTbgSbmplfBufffrSGIX (void);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTAGSAMPLEBUFFERSGIXPROC) (void);
#fndif

#ifndff GL_SGIX_polynomibl_ffd
#dffinf GL_SGIX_polynomibl_ffd 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDfformbtionMbp3dSGIX (GLfnum, GLdoublf, GLdoublf, GLint, GLint, GLdoublf, GLdoublf, GLint, GLint, GLdoublf, GLdoublf, GLint, GLint, donst GLdoublf *);
GLAPI void APIENTRY glDfformbtionMbp3fSGIX (GLfnum, GLflobt, GLflobt, GLint, GLint, GLflobt, GLflobt, GLint, GLint, GLflobt, GLflobt, GLint, GLint, donst GLflobt *);
GLAPI void APIENTRY glDfformSGIX (GLbitfifld);
GLAPI void APIENTRY glLobdIdfntityDfformbtionMbpSGIX (GLbitfifld);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDEFORMATIONMAP3DSGIXPROC) (GLfnum tbrgft, GLdoublf u1, GLdoublf u2, GLint ustridf, GLint uordfr, GLdoublf v1, GLdoublf v2, GLint vstridf, GLint vordfr, GLdoublf w1, GLdoublf w2, GLint wstridf, GLint wordfr, donst GLdoublf *points);
typfdff void (APIENTRYP PFNGLDEFORMATIONMAP3FSGIXPROC) (GLfnum tbrgft, GLflobt u1, GLflobt u2, GLint ustridf, GLint uordfr, GLflobt v1, GLflobt v2, GLint vstridf, GLint vordfr, GLflobt w1, GLflobt w2, GLint wstridf, GLint wordfr, donst GLflobt *points);
typfdff void (APIENTRYP PFNGLDEFORMSGIXPROC) (GLbitfifld mbsk);
typfdff void (APIENTRYP PFNGLLOADIDENTITYDEFORMATIONMAPSGIXPROC) (GLbitfifld mbsk);
#fndif

#ifndff GL_SGIX_rfffrfndf_plbnf
#dffinf GL_SGIX_rfffrfndf_plbnf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glRfffrfndfPlbnfSGIX (donst GLdoublf *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLREFERENCEPLANESGIXPROC) (donst GLdoublf *fqubtion);
#fndif

#ifndff GL_SGIX_flusi_rbstfr
#dffinf GL_SGIX_flusi_rbstfr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFlusiRbstfrSGIX (void);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFLUSHRASTERSGIXPROC) (void);
#fndif

#ifndff GL_SGIX_dfpti_tfxturf
#dffinf GL_SGIX_dfpti_tfxturf 1
#fndif

#ifndff GL_SGIS_fog_fundtion
#dffinf GL_SGIS_fog_fundtion 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFogFundSGIS (GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glGftFogFundSGIS (GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFOGFUNCSGISPROC) (GLsizfi n, donst GLflobt *points);
typfdff void (APIENTRYP PFNGLGETFOGFUNCSGISPROC) (GLflobt *points);
#fndif

#ifndff GL_SGIX_fog_offsft
#dffinf GL_SGIX_fog_offsft 1
#fndif

#ifndff GL_HP_imbgf_trbnsform
#dffinf GL_HP_imbgf_trbnsform 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glImbgfTrbnsformPbrbmftfriHP (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glImbgfTrbnsformPbrbmftfrfHP (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glImbgfTrbnsformPbrbmftfrivHP (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glImbgfTrbnsformPbrbmftfrfvHP (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glGftImbgfTrbnsformPbrbmftfrivHP (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftImbgfTrbnsformPbrbmftfrfvHP (GLfnum, GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLIMAGETRANSFORMPARAMETERIHPPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLIMAGETRANSFORMPARAMETERFHPPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLIMAGETRANSFORMPARAMETERIVHPPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLIMAGETRANSFORMPARAMETERFVHPPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETIMAGETRANSFORMPARAMETERIVHPPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETIMAGETRANSFORMPARAMETERFVHPPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
#fndif

#ifndff GL_HP_donvolution_bordfr_modfs
#dffinf GL_HP_donvolution_bordfr_modfs 1
#fndif

#ifndff GL_SGIX_tfxturf_bdd_fnv
#dffinf GL_SGIX_tfxturf_bdd_fnv 1
#fndif

#ifndff GL_EXT_dolor_subtbblf
#dffinf GL_EXT_dolor_subtbblf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glColorSubTbblfEXT (GLfnum, GLsizfi, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glCopyColorSubTbblfEXT (GLfnum, GLsizfi, GLint, GLint, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOLORSUBTABLEEXTPROC) (GLfnum tbrgft, GLsizfi stbrt, GLsizfi dount, GLfnum formbt, GLfnum typf, donst GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLCOPYCOLORSUBTABLEEXTPROC) (GLfnum tbrgft, GLsizfi stbrt, GLint x, GLint y, GLsizfi widti);
#fndif

#ifndff GL_PGI_vfrtfx_iints
#dffinf GL_PGI_vfrtfx_iints 1
#fndif

#ifndff GL_PGI_misd_iints
#dffinf GL_PGI_misd_iints 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glHintPGI (GLfnum, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLHINTPGIPROC) (GLfnum tbrgft, GLint modf);
#fndif

#ifndff GL_EXT_pblfttfd_tfxturf
#dffinf GL_EXT_pblfttfd_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glColorTbblfEXT (GLfnum, GLfnum, GLsizfi, GLfnum, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glGftColorTbblfEXT (GLfnum, GLfnum, GLfnum, GLvoid *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfrivEXT (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftColorTbblfPbrbmftfrfvEXT (GLfnum, GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOLORTABLEEXTPROC) (GLfnum tbrgft, GLfnum intfrnblFormbt, GLsizfi widti, GLfnum formbt, GLfnum typf, donst GLvoid *tbblf);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEEXTPROC) (GLfnum tbrgft, GLfnum formbt, GLfnum typf, GLvoid *dbtb);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOLORTABLEPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
#fndif

#ifndff GL_EXT_dlip_volumf_iint
#dffinf GL_EXT_dlip_volumf_iint 1
#fndif

#ifndff GL_SGIX_list_priority
#dffinf GL_SGIX_list_priority 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGftListPbrbmftfrfvSGIX (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftListPbrbmftfrivSGIX (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glListPbrbmftfrfSGIX (GLuint, GLfnum, GLflobt);
GLAPI void APIENTRY glListPbrbmftfrfvSGIX (GLuint, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glListPbrbmftfriSGIX (GLuint, GLfnum, GLint);
GLAPI void APIENTRY glListPbrbmftfrivSGIX (GLuint, GLfnum, donst GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGETLISTPARAMETERFVSGIXPROC) (GLuint list, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETLISTPARAMETERIVSGIXPROC) (GLuint list, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLLISTPARAMETERFSGIXPROC) (GLuint list, GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLLISTPARAMETERFVSGIXPROC) (GLuint list, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLLISTPARAMETERISGIXPROC) (GLuint list, GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLLISTPARAMETERIVSGIXPROC) (GLuint list, GLfnum pnbmf, donst GLint *pbrbms);
#fndif

#ifndff GL_SGIX_ir_instrumfnt1
#dffinf GL_SGIX_ir_instrumfnt1 1
#fndif

#ifndff GL_SGIX_dblligrbpiid_frbgmfnt
#dffinf GL_SGIX_dblligrbpiid_frbgmfnt 1
#fndif

#ifndff GL_SGIX_tfxturf_lod_bibs
#dffinf GL_SGIX_tfxturf_lod_bibs 1
#fndif

#ifndff GL_SGIX_sibdow_bmbifnt
#dffinf GL_SGIX_sibdow_bmbifnt 1
#fndif

#ifndff GL_EXT_indfx_tfxturf
#dffinf GL_EXT_indfx_tfxturf 1
#fndif

#ifndff GL_EXT_indfx_mbtfribl
#dffinf GL_EXT_indfx_mbtfribl 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glIndfxMbtfriblEXT (GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLINDEXMATERIALEXTPROC) (GLfnum fbdf, GLfnum modf);
#fndif

#ifndff GL_EXT_indfx_fund
#dffinf GL_EXT_indfx_fund 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glIndfxFundEXT (GLfnum, GLdlbmpf);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLINDEXFUNCEXTPROC) (GLfnum fund, GLdlbmpf rff);
#fndif

#ifndff GL_EXT_indfx_brrby_formbts
#dffinf GL_EXT_indfx_brrby_formbts 1
#fndif

#ifndff GL_EXT_dompilfd_vfrtfx_brrby
#dffinf GL_EXT_dompilfd_vfrtfx_brrby 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glLodkArrbysEXT (GLint, GLsizfi);
GLAPI void APIENTRY glUnlodkArrbysEXT (void);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLLOCKARRAYSEXTPROC) (GLint first, GLsizfi dount);
typfdff void (APIENTRYP PFNGLUNLOCKARRAYSEXTPROC) (void);
#fndif

#ifndff GL_EXT_dull_vfrtfx
#dffinf GL_EXT_dull_vfrtfx 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glCullPbrbmftfrdvEXT (GLfnum, GLdoublf *);
GLAPI void APIENTRY glCullPbrbmftfrfvEXT (GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCULLPARAMETERDVEXTPROC) (GLfnum pnbmf, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLCULLPARAMETERFVEXTPROC) (GLfnum pnbmf, GLflobt *pbrbms);
#fndif

#ifndff GL_SGIX_ydrdb
#dffinf GL_SGIX_ydrdb 1
#fndif

#ifndff GL_SGIX_frbgmfnt_ligiting
#dffinf GL_SGIX_frbgmfnt_ligiting 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFrbgmfntColorMbtfriblSGIX (GLfnum, GLfnum);
GLAPI void APIENTRY glFrbgmfntLigitfSGIX (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glFrbgmfntLigitfvSGIX (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glFrbgmfntLigitiSGIX (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glFrbgmfntLigitivSGIX (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glFrbgmfntLigitModflfSGIX (GLfnum, GLflobt);
GLAPI void APIENTRY glFrbgmfntLigitModflfvSGIX (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glFrbgmfntLigitModfliSGIX (GLfnum, GLint);
GLAPI void APIENTRY glFrbgmfntLigitModflivSGIX (GLfnum, donst GLint *);
GLAPI void APIENTRY glFrbgmfntMbtfriblfSGIX (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glFrbgmfntMbtfriblfvSGIX (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glFrbgmfntMbtfribliSGIX (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glFrbgmfntMbtfriblivSGIX (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glGftFrbgmfntLigitfvSGIX (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftFrbgmfntLigitivSGIX (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftFrbgmfntMbtfriblfvSGIX (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftFrbgmfntMbtfriblivSGIX (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glLigitEnviSGIX (GLfnum, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFRAGMENTCOLORMATERIALSGIXPROC) (GLfnum fbdf, GLfnum modf);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTFSGIXPROC) (GLfnum ligit, GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTFVSGIXPROC) (GLfnum ligit, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTISGIXPROC) (GLfnum ligit, GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTIVSGIXPROC) (GLfnum ligit, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTMODELFSGIXPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTMODELFVSGIXPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTMODELISGIXPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTLIGHTMODELIVSGIXPROC) (GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLFRAGMENTMATERIALFSGIXPROC) (GLfnum fbdf, GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTMATERIALFVSGIXPROC) (GLfnum fbdf, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLFRAGMENTMATERIALISGIXPROC) (GLfnum fbdf, GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLFRAGMENTMATERIALIVSGIXPROC) (GLfnum fbdf, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETFRAGMENTLIGHTFVSGIXPROC) (GLfnum ligit, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETFRAGMENTLIGHTIVSGIXPROC) (GLfnum ligit, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETFRAGMENTMATERIALFVSGIXPROC) (GLfnum fbdf, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETFRAGMENTMATERIALIVSGIXPROC) (GLfnum fbdf, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLLIGHTENVISGIXPROC) (GLfnum pnbmf, GLint pbrbm);
#fndif

#ifndff GL_IBM_rbstfrpos_dlip
#dffinf GL_IBM_rbstfrpos_dlip 1
#fndif

#ifndff GL_HP_tfxturf_ligiting
#dffinf GL_HP_tfxturf_ligiting 1
#fndif

#ifndff GL_EXT_drbw_rbngf_flfmfnts
#dffinf GL_EXT_drbw_rbngf_flfmfnts 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDrbwRbngfElfmfntsEXT (GLfnum, GLuint, GLuint, GLsizfi, GLfnum, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDRAWRANGEELEMENTSEXTPROC) (GLfnum modf, GLuint stbrt, GLuint fnd, GLsizfi dount, GLfnum typf, donst GLvoid *indidfs);
#fndif

#ifndff GL_WIN_piong_sibding
#dffinf GL_WIN_piong_sibding 1
#fndif

#ifndff GL_WIN_spfdulbr_fog
#dffinf GL_WIN_spfdulbr_fog 1
#fndif

#ifndff GL_EXT_ligit_tfxturf
#dffinf GL_EXT_ligit_tfxturf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glApplyTfxturfEXT (GLfnum);
GLAPI void APIENTRY glTfxturfLigitEXT (GLfnum);
GLAPI void APIENTRY glTfxturfMbtfriblEXT (GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLAPPLYTEXTUREEXTPROC) (GLfnum modf);
typfdff void (APIENTRYP PFNGLTEXTURELIGHTEXTPROC) (GLfnum pnbmf);
typfdff void (APIENTRYP PFNGLTEXTUREMATERIALEXTPROC) (GLfnum fbdf, GLfnum modf);
#fndif

#ifndff GL_SGIX_blfnd_blpib_minmbx
#dffinf GL_SGIX_blfnd_blpib_minmbx 1
#fndif

#ifndff GL_EXT_bgrb
#dffinf GL_EXT_bgrb 1
#fndif

#ifndff GL_SGIX_bsynd
#dffinf GL_SGIX_bsynd 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glAsyndMbrkfrSGIX (GLuint);
GLAPI GLint APIENTRY glFinisiAsyndSGIX (GLuint *);
GLAPI GLint APIENTRY glPollAsyndSGIX (GLuint *);
GLAPI GLuint APIENTRY glGfnAsyndMbrkfrsSGIX (GLsizfi);
GLAPI void APIENTRY glDflftfAsyndMbrkfrsSGIX (GLuint, GLsizfi);
GLAPI GLboolfbn APIENTRY glIsAsyndMbrkfrSGIX (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLASYNCMARKERSGIXPROC) (GLuint mbrkfr);
typfdff GLint (APIENTRYP PFNGLFINISHASYNCSGIXPROC) (GLuint *mbrkfrp);
typfdff GLint (APIENTRYP PFNGLPOLLASYNCSGIXPROC) (GLuint *mbrkfrp);
typfdff GLuint (APIENTRYP PFNGLGENASYNCMARKERSSGIXPROC) (GLsizfi rbngf);
typfdff void (APIENTRYP PFNGLDELETEASYNCMARKERSSGIXPROC) (GLuint mbrkfr, GLsizfi rbngf);
typfdff GLboolfbn (APIENTRYP PFNGLISASYNCMARKERSGIXPROC) (GLuint mbrkfr);
#fndif

#ifndff GL_SGIX_bsynd_pixfl
#dffinf GL_SGIX_bsynd_pixfl 1
#fndif

#ifndff GL_SGIX_bsynd_iistogrbm
#dffinf GL_SGIX_bsynd_iistogrbm 1
#fndif

#ifndff GL_INTEL_pbrbllfl_brrbys
#dffinf GL_INTEL_pbrbllfl_brrbys 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxPointfrvINTEL (GLint, GLfnum, donst GLvoid* *);
GLAPI void APIENTRY glNormblPointfrvINTEL (GLfnum, donst GLvoid* *);
GLAPI void APIENTRY glColorPointfrvINTEL (GLint, GLfnum, donst GLvoid* *);
GLAPI void APIENTRY glTfxCoordPointfrvINTEL (GLint, GLfnum, donst GLvoid* *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXPOINTERVINTELPROC) (GLint sizf, GLfnum typf, donst GLvoid* *pointfr);
typfdff void (APIENTRYP PFNGLNORMALPOINTERVINTELPROC) (GLfnum typf, donst GLvoid* *pointfr);
typfdff void (APIENTRYP PFNGLCOLORPOINTERVINTELPROC) (GLint sizf, GLfnum typf, donst GLvoid* *pointfr);
typfdff void (APIENTRYP PFNGLTEXCOORDPOINTERVINTELPROC) (GLint sizf, GLfnum typf, donst GLvoid* *pointfr);
#fndif

#ifndff GL_HP_oddlusion_tfst
#dffinf GL_HP_oddlusion_tfst 1
#fndif

#ifndff GL_EXT_pixfl_trbnsform
#dffinf GL_EXT_pixfl_trbnsform 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPixflTrbnsformPbrbmftfriEXT (GLfnum, GLfnum, GLint);
GLAPI void APIENTRY glPixflTrbnsformPbrbmftfrfEXT (GLfnum, GLfnum, GLflobt);
GLAPI void APIENTRY glPixflTrbnsformPbrbmftfrivEXT (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glPixflTrbnsformPbrbmftfrfvEXT (GLfnum, GLfnum, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPIXELTRANSFORMPARAMETERIEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLPIXELTRANSFORMPARAMETERFEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLPIXELTRANSFORMPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLPIXELTRANSFORMPARAMETERFVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
#fndif

#ifndff GL_EXT_pixfl_trbnsform_dolor_tbblf
#dffinf GL_EXT_pixfl_trbnsform_dolor_tbblf 1
#fndif

#ifndff GL_EXT_sibrfd_tfxturf_pblfttf
#dffinf GL_EXT_sibrfd_tfxturf_pblfttf 1
#fndif

#ifndff GL_EXT_sfpbrbtf_spfdulbr_dolor
#dffinf GL_EXT_sfpbrbtf_spfdulbr_dolor 1
#fndif

#ifndff GL_EXT_sfdondbry_dolor
#dffinf GL_EXT_sfdondbry_dolor 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSfdondbryColor3bEXT (GLbytf, GLbytf, GLbytf);
GLAPI void APIENTRY glSfdondbryColor3bvEXT (donst GLbytf *);
GLAPI void APIENTRY glSfdondbryColor3dEXT (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glSfdondbryColor3dvEXT (donst GLdoublf *);
GLAPI void APIENTRY glSfdondbryColor3fEXT (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glSfdondbryColor3fvEXT (donst GLflobt *);
GLAPI void APIENTRY glSfdondbryColor3iEXT (GLint, GLint, GLint);
GLAPI void APIENTRY glSfdondbryColor3ivEXT (donst GLint *);
GLAPI void APIENTRY glSfdondbryColor3sEXT (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glSfdondbryColor3svEXT (donst GLsiort *);
GLAPI void APIENTRY glSfdondbryColor3ubEXT (GLubytf, GLubytf, GLubytf);
GLAPI void APIENTRY glSfdondbryColor3ubvEXT (donst GLubytf *);
GLAPI void APIENTRY glSfdondbryColor3uiEXT (GLuint, GLuint, GLuint);
GLAPI void APIENTRY glSfdondbryColor3uivEXT (donst GLuint *);
GLAPI void APIENTRY glSfdondbryColor3usEXT (GLusiort, GLusiort, GLusiort);
GLAPI void APIENTRY glSfdondbryColor3usvEXT (donst GLusiort *);
GLAPI void APIENTRY glSfdondbryColorPointfrEXT (GLint, GLfnum, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3BEXTPROC) (GLbytf rfd, GLbytf grffn, GLbytf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3BVEXTPROC) (donst GLbytf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3DEXTPROC) (GLdoublf rfd, GLdoublf grffn, GLdoublf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3DVEXTPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3FEXTPROC) (GLflobt rfd, GLflobt grffn, GLflobt bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3FVEXTPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3IEXTPROC) (GLint rfd, GLint grffn, GLint bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3IVEXTPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3SEXTPROC) (GLsiort rfd, GLsiort grffn, GLsiort bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3SVEXTPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UBEXTPROC) (GLubytf rfd, GLubytf grffn, GLubytf bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UBVEXTPROC) (donst GLubytf *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UIEXTPROC) (GLuint rfd, GLuint grffn, GLuint bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3UIVEXTPROC) (donst GLuint *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3USEXTPROC) (GLusiort rfd, GLusiort grffn, GLusiort bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3USVEXTPROC) (donst GLusiort *v);
typfdff void (APIENTRYP PFNGLSECONDARYCOLORPOINTEREXTPROC) (GLint sizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_EXT_tfxturf_pfrturb_normbl
#dffinf GL_EXT_tfxturf_pfrturb_normbl 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxturfNormblEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXTURENORMALEXTPROC) (GLfnum modf);
#fndif

#ifndff GL_EXT_multi_drbw_brrbys
#dffinf GL_EXT_multi_drbw_brrbys 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glMultiDrbwArrbysEXT (GLfnum, GLint *, GLsizfi *, GLsizfi);
GLAPI void APIENTRY glMultiDrbwElfmfntsEXT (GLfnum, donst GLsizfi *, GLfnum, donst GLvoid* *, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLMULTIDRAWARRAYSEXTPROC) (GLfnum modf, GLint *first, GLsizfi *dount, GLsizfi primdount);
typfdff void (APIENTRYP PFNGLMULTIDRAWELEMENTSEXTPROC) (GLfnum modf, donst GLsizfi *dount, GLfnum typf, donst GLvoid* *indidfs, GLsizfi primdount);
#fndif

#ifndff GL_EXT_fog_doord
#dffinf GL_EXT_fog_doord 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFogCoordfEXT (GLflobt);
GLAPI void APIENTRY glFogCoordfvEXT (donst GLflobt *);
GLAPI void APIENTRY glFogCoorddEXT (GLdoublf);
GLAPI void APIENTRY glFogCoorddvEXT (donst GLdoublf *);
GLAPI void APIENTRY glFogCoordPointfrEXT (GLfnum, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFOGCOORDFEXTPROC) (GLflobt doord);
typfdff void (APIENTRYP PFNGLFOGCOORDFVEXTPROC) (donst GLflobt *doord);
typfdff void (APIENTRYP PFNGLFOGCOORDDEXTPROC) (GLdoublf doord);
typfdff void (APIENTRYP PFNGLFOGCOORDDVEXTPROC) (donst GLdoublf *doord);
typfdff void (APIENTRYP PFNGLFOGCOORDPOINTEREXTPROC) (GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_REND_sdrffn_doordinbtfs
#dffinf GL_REND_sdrffn_doordinbtfs 1
#fndif

#ifndff GL_EXT_doordinbtf_frbmf
#dffinf GL_EXT_doordinbtf_frbmf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTbngfnt3bEXT (GLbytf, GLbytf, GLbytf);
GLAPI void APIENTRY glTbngfnt3bvEXT (donst GLbytf *);
GLAPI void APIENTRY glTbngfnt3dEXT (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glTbngfnt3dvEXT (donst GLdoublf *);
GLAPI void APIENTRY glTbngfnt3fEXT (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTbngfnt3fvEXT (donst GLflobt *);
GLAPI void APIENTRY glTbngfnt3iEXT (GLint, GLint, GLint);
GLAPI void APIENTRY glTbngfnt3ivEXT (donst GLint *);
GLAPI void APIENTRY glTbngfnt3sEXT (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glTbngfnt3svEXT (donst GLsiort *);
GLAPI void APIENTRY glBinormbl3bEXT (GLbytf, GLbytf, GLbytf);
GLAPI void APIENTRY glBinormbl3bvEXT (donst GLbytf *);
GLAPI void APIENTRY glBinormbl3dEXT (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glBinormbl3dvEXT (donst GLdoublf *);
GLAPI void APIENTRY glBinormbl3fEXT (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glBinormbl3fvEXT (donst GLflobt *);
GLAPI void APIENTRY glBinormbl3iEXT (GLint, GLint, GLint);
GLAPI void APIENTRY glBinormbl3ivEXT (donst GLint *);
GLAPI void APIENTRY glBinormbl3sEXT (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glBinormbl3svEXT (donst GLsiort *);
GLAPI void APIENTRY glTbngfntPointfrEXT (GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glBinormblPointfrEXT (GLfnum, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTANGENT3BEXTPROC) (GLbytf tx, GLbytf ty, GLbytf tz);
typfdff void (APIENTRYP PFNGLTANGENT3BVEXTPROC) (donst GLbytf *v);
typfdff void (APIENTRYP PFNGLTANGENT3DEXTPROC) (GLdoublf tx, GLdoublf ty, GLdoublf tz);
typfdff void (APIENTRYP PFNGLTANGENT3DVEXTPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLTANGENT3FEXTPROC) (GLflobt tx, GLflobt ty, GLflobt tz);
typfdff void (APIENTRYP PFNGLTANGENT3FVEXTPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTANGENT3IEXTPROC) (GLint tx, GLint ty, GLint tz);
typfdff void (APIENTRYP PFNGLTANGENT3IVEXTPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLTANGENT3SEXTPROC) (GLsiort tx, GLsiort ty, GLsiort tz);
typfdff void (APIENTRYP PFNGLTANGENT3SVEXTPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLBINORMAL3BEXTPROC) (GLbytf bx, GLbytf by, GLbytf bz);
typfdff void (APIENTRYP PFNGLBINORMAL3BVEXTPROC) (donst GLbytf *v);
typfdff void (APIENTRYP PFNGLBINORMAL3DEXTPROC) (GLdoublf bx, GLdoublf by, GLdoublf bz);
typfdff void (APIENTRYP PFNGLBINORMAL3DVEXTPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLBINORMAL3FEXTPROC) (GLflobt bx, GLflobt by, GLflobt bz);
typfdff void (APIENTRYP PFNGLBINORMAL3FVEXTPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLBINORMAL3IEXTPROC) (GLint bx, GLint by, GLint bz);
typfdff void (APIENTRYP PFNGLBINORMAL3IVEXTPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLBINORMAL3SEXTPROC) (GLsiort bx, GLsiort by, GLsiort bz);
typfdff void (APIENTRYP PFNGLBINORMAL3SVEXTPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLTANGENTPOINTEREXTPROC) (GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLBINORMALPOINTEREXTPROC) (GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_EXT_tfxturf_fnv_dombinf
#dffinf GL_EXT_tfxturf_fnv_dombinf 1
#fndif

#ifndff GL_APPLE_spfdulbr_vfdtor
#dffinf GL_APPLE_spfdulbr_vfdtor 1
#fndif

#ifndff GL_APPLE_trbnsform_iint
#dffinf GL_APPLE_trbnsform_iint 1
#fndif

#ifndff GL_SGIX_fog_sdblf
#dffinf GL_SGIX_fog_sdblf 1
#fndif

#ifndff GL_SUNX_donstbnt_dbtb
#dffinf GL_SUNX_donstbnt_dbtb 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFinisiTfxturfSUNX (void);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFINISHTEXTURESUNXPROC) (void);
#fndif

#ifndff GL_SUN_globbl_blpib
#dffinf GL_SUN_globbl_blpib 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGlobblAlpibFbdtorbSUN (GLbytf);
GLAPI void APIENTRY glGlobblAlpibFbdtorsSUN (GLsiort);
GLAPI void APIENTRY glGlobblAlpibFbdtoriSUN (GLint);
GLAPI void APIENTRY glGlobblAlpibFbdtorfSUN (GLflobt);
GLAPI void APIENTRY glGlobblAlpibFbdtordSUN (GLdoublf);
GLAPI void APIENTRY glGlobblAlpibFbdtorubSUN (GLubytf);
GLAPI void APIENTRY glGlobblAlpibFbdtorusSUN (GLusiort);
GLAPI void APIENTRY glGlobblAlpibFbdtoruiSUN (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORBSUNPROC) (GLbytf fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORSSUNPROC) (GLsiort fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORISUNPROC) (GLint fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORFSUNPROC) (GLflobt fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORDSUNPROC) (GLdoublf fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORUBSUNPROC) (GLubytf fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORUSSUNPROC) (GLusiort fbdtor);
typfdff void (APIENTRYP PFNGLGLOBALALPHAFACTORUISUNPROC) (GLuint fbdtor);
#fndif

#ifndff GL_SUN_tribnglf_list
#dffinf GL_SUN_tribnglf_list 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glRfplbdfmfntCodfuiSUN (GLuint);
GLAPI void APIENTRY glRfplbdfmfntCodfusSUN (GLusiort);
GLAPI void APIENTRY glRfplbdfmfntCodfubSUN (GLubytf);
GLAPI void APIENTRY glRfplbdfmfntCodfuivSUN (donst GLuint *);
GLAPI void APIENTRY glRfplbdfmfntCodfusvSUN (donst GLusiort *);
GLAPI void APIENTRY glRfplbdfmfntCodfubvSUN (donst GLubytf *);
GLAPI void APIENTRY glRfplbdfmfntCodfPointfrSUN (GLfnum, GLsizfi, donst GLvoid* *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUISUNPROC) (GLuint dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUSSUNPROC) (GLusiort dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUBSUNPROC) (GLubytf dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUIVSUNPROC) (donst GLuint *dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUSVSUNPROC) (donst GLusiort *dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUBVSUNPROC) (donst GLubytf *dodf);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEPOINTERSUNPROC) (GLfnum typf, GLsizfi stridf, donst GLvoid* *pointfr);
#fndif

#ifndff GL_SUN_vfrtfx
#dffinf GL_SUN_vfrtfx 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glColor4ubVfrtfx2fSUN (GLubytf, GLubytf, GLubytf, GLubytf, GLflobt, GLflobt);
GLAPI void APIENTRY glColor4ubVfrtfx2fvSUN (donst GLubytf *, donst GLflobt *);
GLAPI void APIENTRY glColor4ubVfrtfx3fSUN (GLubytf, GLubytf, GLubytf, GLubytf, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glColor4ubVfrtfx3fvSUN (donst GLubytf *, donst GLflobt *);
GLAPI void APIENTRY glColor3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glColor3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glNormbl3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glNormbl3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glColor4fNormbl3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glColor4fNormbl3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord2fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord2fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord4fVfrtfx4fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord4fVfrtfx4fvSUN (donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord2fColor4ubVfrtfx3fSUN (GLflobt, GLflobt, GLubytf, GLubytf, GLubytf, GLubytf, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord2fColor4ubVfrtfx3fvSUN (donst GLflobt *, donst GLubytf *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord2fColor3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord2fColor3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord2fNormbl3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord2fNormbl3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord2fColor4fNormbl3fVfrtfx3fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord2fColor4fNormbl3fVfrtfx3fvSUN (donst GLflobt *, donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glTfxCoord4fColor4fNormbl3fVfrtfx4fSUN (GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glTfxCoord4fColor4fNormbl3fVfrtfx4fvSUN (donst GLflobt *, donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiVfrtfx3fvSUN (donst GLuint *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor4ubVfrtfx3fSUN (GLuint, GLubytf, GLubytf, GLubytf, GLubytf, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor4ubVfrtfx3fvSUN (donst GLuint *, donst GLubytf *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor3fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor3fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiNormbl3fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiNormbl3fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor4fNormbl3fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiColor4fNormbl3fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fNormbl3fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fNormbl3fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *, donst GLflobt *);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fColor4fNormbl3fVfrtfx3fSUN (GLuint, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glRfplbdfmfntCodfuiTfxCoord2fColor4fNormbl3fVfrtfx3fvSUN (donst GLuint *, donst GLflobt *, donst GLflobt *, donst GLflobt *, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOLOR4UBVERTEX2FSUNPROC) (GLubytf r, GLubytf g, GLubytf b, GLubytf b, GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLCOLOR4UBVERTEX2FVSUNPROC) (donst GLubytf *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLCOLOR4UBVERTEX3FSUNPROC) (GLubytf r, GLubytf g, GLubytf b, GLubytf b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLCOLOR4UBVERTEX3FVSUNPROC) (donst GLubytf *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLCOLOR3FVERTEX3FSUNPROC) (GLflobt r, GLflobt g, GLflobt b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLCOLOR3FVERTEX3FVSUNPROC) (donst GLflobt *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLNORMAL3FVERTEX3FSUNPROC) (GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLNORMAL3FVERTEX3FVSUNPROC) (donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLCOLOR4FNORMAL3FVERTEX3FSUNPROC) (GLflobt r, GLflobt g, GLflobt b, GLflobt b, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLCOLOR4FNORMAL3FVERTEX3FVSUNPROC) (donst GLflobt *d, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2FVERTEX3FSUNPROC) (GLflobt s, GLflobt t, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLTEXCOORD2FVERTEX3FVSUNPROC) (donst GLflobt *td, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD4FVERTEX4FSUNPROC) (GLflobt s, GLflobt t, GLflobt p, GLflobt q, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLTEXCOORD4FVERTEX4FVSUNPROC) (donst GLflobt *td, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR4UBVERTEX3FSUNPROC) (GLflobt s, GLflobt t, GLubytf r, GLubytf g, GLubytf b, GLubytf b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR4UBVERTEX3FVSUNPROC) (donst GLflobt *td, donst GLubytf *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR3FVERTEX3FSUNPROC) (GLflobt s, GLflobt t, GLflobt r, GLflobt g, GLflobt b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR3FVERTEX3FVSUNPROC) (donst GLflobt *td, donst GLflobt *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2FNORMAL3FVERTEX3FSUNPROC) (GLflobt s, GLflobt t, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLTEXCOORD2FNORMAL3FVERTEX3FVSUNPROC) (donst GLflobt *td, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR4FNORMAL3FVERTEX3FSUNPROC) (GLflobt s, GLflobt t, GLflobt r, GLflobt g, GLflobt b, GLflobt b, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLTEXCOORD2FCOLOR4FNORMAL3FVERTEX3FVSUNPROC) (donst GLflobt *td, donst GLflobt *d, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLTEXCOORD4FCOLOR4FNORMAL3FVERTEX4FSUNPROC) (GLflobt s, GLflobt t, GLflobt p, GLflobt q, GLflobt r, GLflobt g, GLflobt b, GLflobt b, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLTEXCOORD4FCOLOR4FNORMAL3FVERTEX4FVSUNPROC) (donst GLflobt *td, donst GLflobt *d, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUIVERTEX3FSUNPROC) (GLuint rd, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUIVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR4UBVERTEX3FSUNPROC) (GLuint rd, GLubytf r, GLubytf g, GLubytf b, GLubytf b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR4UBVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLubytf *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR3FVERTEX3FSUNPROC) (GLuint rd, GLflobt r, GLflobt g, GLflobt b, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR3FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *d, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUINORMAL3FVERTEX3FSUNPROC) (GLuint rd, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUINORMAL3FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR4FNORMAL3FVERTEX3FSUNPROC) (GLuint rd, GLflobt r, GLflobt g, GLflobt b, GLflobt b, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUICOLOR4FNORMAL3FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *d, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FVERTEX3FSUNPROC) (GLuint rd, GLflobt s, GLflobt t, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *td, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FNORMAL3FVERTEX3FSUNPROC) (GLuint rd, GLflobt s, GLflobt t, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FNORMAL3FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *td, donst GLflobt *n, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FCOLOR4FNORMAL3FVERTEX3FSUNPROC) (GLuint rd, GLflobt s, GLflobt t, GLflobt r, GLflobt g, GLflobt b, GLflobt b, GLflobt nx, GLflobt ny, GLflobt nz, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLREPLACEMENTCODEUITEXCOORD2FCOLOR4FNORMAL3FVERTEX3FVSUNPROC) (donst GLuint *rd, donst GLflobt *td, donst GLflobt *d, donst GLflobt *n, donst GLflobt *v);
#fndif

#ifndff GL_EXT_blfnd_fund_sfpbrbtf
#dffinf GL_EXT_blfnd_fund_sfpbrbtf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndFundSfpbrbtfEXT (GLfnum, GLfnum, GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDFUNCSEPARATEEXTPROC) (GLfnum sfbdtorRGB, GLfnum dfbdtorRGB, GLfnum sfbdtorAlpib, GLfnum dfbdtorAlpib);
#fndif

#ifndff GL_INGR_blfnd_fund_sfpbrbtf
#dffinf GL_INGR_blfnd_fund_sfpbrbtf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndFundSfpbrbtfINGR (GLfnum, GLfnum, GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDFUNCSEPARATEINGRPROC) (GLfnum sfbdtorRGB, GLfnum dfbdtorRGB, GLfnum sfbdtorAlpib, GLfnum dfbdtorAlpib);
#fndif

#ifndff GL_INGR_dolor_dlbmp
#dffinf GL_INGR_dolor_dlbmp 1
#fndif

#ifndff GL_INGR_intfrlbdf_rfbd
#dffinf GL_INGR_intfrlbdf_rfbd 1
#fndif

#ifndff GL_EXT_stfndil_wrbp
#dffinf GL_EXT_stfndil_wrbp 1
#fndif

#ifndff GL_EXT_422_pixfls
#dffinf GL_EXT_422_pixfls 1
#fndif

#ifndff GL_NV_tfxgfn_rfflfdtion
#dffinf GL_NV_tfxgfn_rfflfdtion 1
#fndif

#ifndff GL_SUN_donvolution_bordfr_modfs
#dffinf GL_SUN_donvolution_bordfr_modfs 1
#fndif

#ifndff GL_EXT_tfxturf_fnv_bdd
#dffinf GL_EXT_tfxturf_fnv_bdd 1
#fndif

#ifndff GL_EXT_tfxturf_lod_bibs
#dffinf GL_EXT_tfxturf_lod_bibs 1
#fndif

#ifndff GL_EXT_tfxturf_filtfr_bnisotropid
#dffinf GL_EXT_tfxturf_filtfr_bnisotropid 1
#fndif

#ifndff GL_EXT_vfrtfx_wfigiting
#dffinf GL_EXT_vfrtfx_wfigiting 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxWfigitfEXT (GLflobt);
GLAPI void APIENTRY glVfrtfxWfigitfvEXT (donst GLflobt *);
GLAPI void APIENTRY glVfrtfxWfigitPointfrEXT (GLsizfi, GLfnum, GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXWEIGHTFEXTPROC) (GLflobt wfigit);
typfdff void (APIENTRYP PFNGLVERTEXWEIGHTFVEXTPROC) (donst GLflobt *wfigit);
typfdff void (APIENTRYP PFNGLVERTEXWEIGHTPOINTEREXTPROC) (GLsizfi sizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
#fndif

#ifndff GL_NV_ligit_mbx_fxponfnt
#dffinf GL_NV_ligit_mbx_fxponfnt 1
#fndif

#ifndff GL_NV_vfrtfx_brrby_rbngf
#dffinf GL_NV_vfrtfx_brrby_rbngf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glFlusiVfrtfxArrbyRbngfNV (void);
GLAPI void APIENTRY glVfrtfxArrbyRbngfNV (GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLFLUSHVERTEXARRAYRANGENVPROC) (void);
typfdff void (APIENTRYP PFNGLVERTEXARRAYRANGENVPROC) (GLsizfi lfngti, donst GLvoid *pointfr);
#fndif

#ifndff GL_NV_rfgistfr_dombinfrs
#dffinf GL_NV_rfgistfr_dombinfrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glCombinfrPbrbmftfrfvNV (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glCombinfrPbrbmftfrfNV (GLfnum, GLflobt);
GLAPI void APIENTRY glCombinfrPbrbmftfrivNV (GLfnum, donst GLint *);
GLAPI void APIENTRY glCombinfrPbrbmftfriNV (GLfnum, GLint);
GLAPI void APIENTRY glCombinfrInputNV (GLfnum, GLfnum, GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glCombinfrOutputNV (GLfnum, GLfnum, GLfnum, GLfnum, GLfnum, GLfnum, GLfnum, GLboolfbn, GLboolfbn, GLboolfbn);
GLAPI void APIENTRY glFinblCombinfrInputNV (GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glGftCombinfrInputPbrbmftfrfvNV (GLfnum, GLfnum, GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftCombinfrInputPbrbmftfrivNV (GLfnum, GLfnum, GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftCombinfrOutputPbrbmftfrfvNV (GLfnum, GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftCombinfrOutputPbrbmftfrivNV (GLfnum, GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftFinblCombinfrInputPbrbmftfrfvNV (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftFinblCombinfrInputPbrbmftfrivNV (GLfnum, GLfnum, GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOMBINERPARAMETERFVNVPROC) (GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLCOMBINERPARAMETERFNVPROC) (GLfnum pnbmf, GLflobt pbrbm);
typfdff void (APIENTRYP PFNGLCOMBINERPARAMETERIVNVPROC) (GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLCOMBINERPARAMETERINVPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLCOMBINERINPUTNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum vbribblf, GLfnum input, GLfnum mbpping, GLfnum domponfntUsbgf);
typfdff void (APIENTRYP PFNGLCOMBINEROUTPUTNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum bbOutput, GLfnum ddOutput, GLfnum sumOutput, GLfnum sdblf, GLfnum bibs, GLboolfbn bbDotProdudt, GLboolfbn ddDotProdudt, GLboolfbn muxSum);
typfdff void (APIENTRYP PFNGLFINALCOMBINERINPUTNVPROC) (GLfnum vbribblf, GLfnum input, GLfnum mbpping, GLfnum domponfntUsbgf);
typfdff void (APIENTRYP PFNGLGETCOMBINERINPUTPARAMETERFVNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum vbribblf, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOMBINERINPUTPARAMETERIVNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum vbribblf, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOMBINEROUTPUTPARAMETERFVNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOMBINEROUTPUTPARAMETERIVNVPROC) (GLfnum stbgf, GLfnum portion, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETFINALCOMBINERINPUTPARAMETERFVNVPROC) (GLfnum vbribblf, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETFINALCOMBINERINPUTPARAMETERIVNVPROC) (GLfnum vbribblf, GLfnum pnbmf, GLint *pbrbms);
#fndif

#ifndff GL_NV_fog_distbndf
#dffinf GL_NV_fog_distbndf 1
#fndif

#ifndff GL_NV_tfxgfn_fmboss
#dffinf GL_NV_tfxgfn_fmboss 1
#fndif

#ifndff GL_NV_blfnd_squbrf
#dffinf GL_NV_blfnd_squbrf 1
#fndif

#ifndff GL_NV_tfxturf_fnv_dombinf4
#dffinf GL_NV_tfxturf_fnv_dombinf4 1
#fndif

#ifndff GL_MESA_rfsizf_bufffrs
#dffinf GL_MESA_rfsizf_bufffrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glRfsizfBufffrsMESA (void);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLRESIZEBUFFERSMESAPROC) (void);
#fndif

#ifndff GL_MESA_window_pos
#dffinf GL_MESA_window_pos 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glWindowPos2dMESA (GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos2dvMESA (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos2fMESA (GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos2fvMESA (donst GLflobt *);
GLAPI void APIENTRY glWindowPos2iMESA (GLint, GLint);
GLAPI void APIENTRY glWindowPos2ivMESA (donst GLint *);
GLAPI void APIENTRY glWindowPos2sMESA (GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos2svMESA (donst GLsiort *);
GLAPI void APIENTRY glWindowPos3dMESA (GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos3dvMESA (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos3fMESA (GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos3fvMESA (donst GLflobt *);
GLAPI void APIENTRY glWindowPos3iMESA (GLint, GLint, GLint);
GLAPI void APIENTRY glWindowPos3ivMESA (donst GLint *);
GLAPI void APIENTRY glWindowPos3sMESA (GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos3svMESA (donst GLsiort *);
GLAPI void APIENTRY glWindowPos4dMESA (GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glWindowPos4dvMESA (donst GLdoublf *);
GLAPI void APIENTRY glWindowPos4fMESA (GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glWindowPos4fvMESA (donst GLflobt *);
GLAPI void APIENTRY glWindowPos4iMESA (GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glWindowPos4ivMESA (donst GLint *);
GLAPI void APIENTRY glWindowPos4sMESA (GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glWindowPos4svMESA (donst GLsiort *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLWINDOWPOS2DMESAPROC) (GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2DVMESAPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FMESAPROC) (GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2FVMESAPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IMESAPROC) (GLint x, GLint y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2IVMESAPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SMESAPROC) (GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLWINDOWPOS2SVMESAPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DMESAPROC) (GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3DVMESAPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FMESAPROC) (GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3FVMESAPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IMESAPROC) (GLint x, GLint y, GLint z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3IVMESAPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SMESAPROC) (GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLWINDOWPOS3SVMESAPROC) (donst GLsiort *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS4DMESAPROC) (GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLWINDOWPOS4DVMESAPROC) (donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS4FMESAPROC) (GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLWINDOWPOS4FVMESAPROC) (donst GLflobt *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS4IMESAPROC) (GLint x, GLint y, GLint z, GLint w);
typfdff void (APIENTRYP PFNGLWINDOWPOS4IVMESAPROC) (donst GLint *v);
typfdff void (APIENTRYP PFNGLWINDOWPOS4SMESAPROC) (GLsiort x, GLsiort y, GLsiort z, GLsiort w);
typfdff void (APIENTRYP PFNGLWINDOWPOS4SVMESAPROC) (donst GLsiort *v);
#fndif

#ifndff GL_IBM_dull_vfrtfx
#dffinf GL_IBM_dull_vfrtfx 1
#fndif

#ifndff GL_IBM_multimodf_drbw_brrbys
#dffinf GL_IBM_multimodf_drbw_brrbys 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glMultiModfDrbwArrbysIBM (donst GLfnum *, donst GLint *, donst GLsizfi *, GLsizfi, GLint);
GLAPI void APIENTRY glMultiModfDrbwElfmfntsIBM (donst GLfnum *, donst GLsizfi *, GLfnum, donst GLvoid* donst *, GLsizfi, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLMULTIMODEDRAWARRAYSIBMPROC) (donst GLfnum *modf, donst GLint *first, donst GLsizfi *dount, GLsizfi primdount, GLint modfstridf);
typfdff void (APIENTRYP PFNGLMULTIMODEDRAWELEMENTSIBMPROC) (donst GLfnum *modf, donst GLsizfi *dount, GLfnum typf, donst GLvoid* donst *indidfs, GLsizfi primdount, GLint modfstridf);
#fndif

#ifndff GL_IBM_vfrtfx_brrby_lists
#dffinf GL_IBM_vfrtfx_brrby_lists 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glColorPointfrListIBM (GLint, GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glSfdondbryColorPointfrListIBM (GLint, GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glEdgfFlbgPointfrListIBM (GLint, donst GLboolfbn* *, GLint);
GLAPI void APIENTRY glFogCoordPointfrListIBM (GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glIndfxPointfrListIBM (GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glNormblPointfrListIBM (GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glTfxCoordPointfrListIBM (GLint, GLfnum, GLint, donst GLvoid* *, GLint);
GLAPI void APIENTRY glVfrtfxPointfrListIBM (GLint, GLfnum, GLint, donst GLvoid* *, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOLORPOINTERLISTIBMPROC) (GLint sizf, GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLORPOINTERLISTIBMPROC) (GLint sizf, GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLEDGEFLAGPOINTERLISTIBMPROC) (GLint stridf, donst GLboolfbn* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLFOGCOORDPOINTERLISTIBMPROC) (GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLINDEXPOINTERLISTIBMPROC) (GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLNORMALPOINTERLISTIBMPROC) (GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLTEXCOORDPOINTERLISTIBMPROC) (GLint sizf, GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
typfdff void (APIENTRYP PFNGLVERTEXPOINTERLISTIBMPROC) (GLint sizf, GLfnum typf, GLint stridf, donst GLvoid* *pointfr, GLint ptrstridf);
#fndif

#ifndff GL_SGIX_subsbmplf
#dffinf GL_SGIX_subsbmplf 1
#fndif

#ifndff GL_SGIX_ydrdbb
#dffinf GL_SGIX_ydrdbb 1
#fndif

#ifndff GL_SGIX_ydrdb_subsbmplf
#dffinf GL_SGIX_ydrdb_subsbmplf 1
#fndif

#ifndff GL_SGIX_dfpti_pbss_instrumfnt
#dffinf GL_SGIX_dfpti_pbss_instrumfnt 1
#fndif

#ifndff GL_3DFX_tfxturf_domprfssion_FXT1
#dffinf GL_3DFX_tfxturf_domprfssion_FXT1 1
#fndif

#ifndff GL_3DFX_multisbmplf
#dffinf GL_3DFX_multisbmplf 1
#fndif

#ifndff GL_3DFX_tbufffr
#dffinf GL_3DFX_tbufffr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTbufffrMbsk3DFX (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTBUFFERMASK3DFXPROC) (GLuint mbsk);
#fndif

#ifndff GL_EXT_multisbmplf
#dffinf GL_EXT_multisbmplf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glSbmplfMbskEXT (GLdlbmpf, GLboolfbn);
GLAPI void APIENTRY glSbmplfPbttfrnEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSAMPLEMASKEXTPROC) (GLdlbmpf vbluf, GLboolfbn invfrt);
typfdff void (APIENTRYP PFNGLSAMPLEPATTERNEXTPROC) (GLfnum pbttfrn);
#fndif

#ifndff GL_SGIX_vfrtfx_prfdlip
#dffinf GL_SGIX_vfrtfx_prfdlip 1
#fndif

#ifndff GL_SGIX_donvolution_bddurbdy
#dffinf GL_SGIX_donvolution_bddurbdy 1
#fndif

#ifndff GL_SGIX_rfsbmplf
#dffinf GL_SGIX_rfsbmplf 1
#fndif

#ifndff GL_SGIS_point_linf_tfxgfn
#dffinf GL_SGIS_point_linf_tfxgfn 1
#fndif

#ifndff GL_SGIS_tfxturf_dolor_mbsk
#dffinf GL_SGIS_tfxturf_dolor_mbsk 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxturfColorMbskSGIS (GLboolfbn, GLboolfbn, GLboolfbn, GLboolfbn);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXTURECOLORMASKSGISPROC) (GLboolfbn rfd, GLboolfbn grffn, GLboolfbn bluf, GLboolfbn blpib);
#fndif

#ifndff GL_SGIX_igloo_intfrfbdf
#dffinf GL_SGIX_igloo_intfrfbdf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glIglooIntfrfbdfSGIX (GLfnum, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLIGLOOINTERFACESGIXPROC) (GLfnum pnbmf, donst GLvoid *pbrbms);
#fndif

#ifndff GL_EXT_tfxturf_fnv_dot3
#dffinf GL_EXT_tfxturf_fnv_dot3 1
#fndif

#ifndff GL_ATI_tfxturf_mirror_ondf
#dffinf GL_ATI_tfxturf_mirror_ondf 1
#fndif

#ifndff GL_NV_ffndf
#dffinf GL_NV_ffndf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDflftfFfndfsNV (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnFfndfsNV (GLsizfi, GLuint *);
GLAPI GLboolfbn APIENTRY glIsFfndfNV (GLuint);
GLAPI GLboolfbn APIENTRY glTfstFfndfNV (GLuint);
GLAPI void APIENTRY glGftFfndfivNV (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glFinisiFfndfNV (GLuint);
GLAPI void APIENTRY glSftFfndfNV (GLuint, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDELETEFENCESNVPROC) (GLsizfi n, donst GLuint *ffndfs);
typfdff void (APIENTRYP PFNGLGENFENCESNVPROC) (GLsizfi n, GLuint *ffndfs);
typfdff GLboolfbn (APIENTRYP PFNGLISFENCENVPROC) (GLuint ffndf);
typfdff GLboolfbn (APIENTRYP PFNGLTESTFENCENVPROC) (GLuint ffndf);
typfdff void (APIENTRYP PFNGLGETFENCEIVNVPROC) (GLuint ffndf, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLFINISHFENCENVPROC) (GLuint ffndf);
typfdff void (APIENTRYP PFNGLSETFENCENVPROC) (GLuint ffndf, GLfnum dondition);
#fndif

#ifndff GL_NV_fvblubtors
#dffinf GL_NV_fvblubtors 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glMbpControlPointsNV (GLfnum, GLuint, GLfnum, GLsizfi, GLsizfi, GLint, GLint, GLboolfbn, donst GLvoid *);
GLAPI void APIENTRY glMbpPbrbmftfrivNV (GLfnum, GLfnum, donst GLint *);
GLAPI void APIENTRY glMbpPbrbmftfrfvNV (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glGftMbpControlPointsNV (GLfnum, GLuint, GLfnum, GLsizfi, GLsizfi, GLboolfbn, GLvoid *);
GLAPI void APIENTRY glGftMbpPbrbmftfrivNV (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGftMbpPbrbmftfrfvNV (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftMbpAttribPbrbmftfrivNV (GLfnum, GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftMbpAttribPbrbmftfrfvNV (GLfnum, GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glEvblMbpsNV (GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLMAPCONTROLPOINTSNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum typf, GLsizfi ustridf, GLsizfi vstridf, GLint uordfr, GLint vordfr, GLboolfbn pbdkfd, donst GLvoid *points);
typfdff void (APIENTRYP PFNGLMAPPARAMETERIVNVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLint *pbrbms);
typfdff void (APIENTRYP PFNGLMAPPARAMETERFVNVPROC) (GLfnum tbrgft, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETMAPCONTROLPOINTSNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum typf, GLsizfi ustridf, GLsizfi vstridf, GLboolfbn pbdkfd, GLvoid *points);
typfdff void (APIENTRYP PFNGLGETMAPPARAMETERIVNVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETMAPPARAMETERFVNVPROC) (GLfnum tbrgft, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETMAPATTRIBPARAMETERIVNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETMAPATTRIBPARAMETERFVNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLEVALMAPSNVPROC) (GLfnum tbrgft, GLfnum modf);
#fndif

#ifndff GL_NV_pbdkfd_dfpti_stfndil
#dffinf GL_NV_pbdkfd_dfpti_stfndil 1
#fndif

#ifndff GL_NV_rfgistfr_dombinfrs2
#dffinf GL_NV_rfgistfr_dombinfrs2 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glCombinfrStbgfPbrbmftfrfvNV (GLfnum, GLfnum, donst GLflobt *);
GLAPI void APIENTRY glGftCombinfrStbgfPbrbmftfrfvNV (GLfnum, GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLCOMBINERSTAGEPARAMETERFVNVPROC) (GLfnum stbgf, GLfnum pnbmf, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETCOMBINERSTAGEPARAMETERFVNVPROC) (GLfnum stbgf, GLfnum pnbmf, GLflobt *pbrbms);
#fndif

#ifndff GL_NV_tfxturf_domprfssion_vtd
#dffinf GL_NV_tfxturf_domprfssion_vtd 1
#fndif

#ifndff GL_NV_tfxturf_rfdtbnglf
#dffinf GL_NV_tfxturf_rfdtbnglf 1
#fndif

#ifndff GL_NV_tfxturf_sibdfr
#dffinf GL_NV_tfxturf_sibdfr 1
#fndif

#ifndff GL_NV_tfxturf_sibdfr2
#dffinf GL_NV_tfxturf_sibdfr2 1
#fndif

#ifndff GL_NV_vfrtfx_brrby_rbngf2
#dffinf GL_NV_vfrtfx_brrby_rbngf2 1
#fndif

#ifndff GL_NV_vfrtfx_progrbm
#dffinf GL_NV_vfrtfx_progrbm 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLboolfbn APIENTRY glArfProgrbmsRfsidfntNV (GLsizfi, donst GLuint *, GLboolfbn *);
GLAPI void APIENTRY glBindProgrbmNV (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfProgrbmsNV (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glExfdutfProgrbmNV (GLfnum, GLuint, donst GLflobt *);
GLAPI void APIENTRY glGfnProgrbmsNV (GLsizfi, GLuint *);
GLAPI void APIENTRY glGftProgrbmPbrbmftfrdvNV (GLfnum, GLuint, GLfnum, GLdoublf *);
GLAPI void APIENTRY glGftProgrbmPbrbmftfrfvNV (GLfnum, GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftProgrbmivNV (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftProgrbmStringNV (GLuint, GLfnum, GLubytf *);
GLAPI void APIENTRY glGftTrbdkMbtrixivNV (GLfnum, GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftVfrtfxAttribdvNV (GLuint, GLfnum, GLdoublf *);
GLAPI void APIENTRY glGftVfrtfxAttribfvNV (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVfrtfxAttribivNV (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftVfrtfxAttribPointfrvNV (GLuint, GLfnum, GLvoid* *);
GLAPI GLboolfbn APIENTRY glIsProgrbmNV (GLuint);
GLAPI void APIENTRY glLobdProgrbmNV (GLfnum, GLuint, GLsizfi, donst GLubytf *);
GLAPI void APIENTRY glProgrbmPbrbmftfr4dNV (GLfnum, GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glProgrbmPbrbmftfr4dvNV (GLfnum, GLuint, donst GLdoublf *);
GLAPI void APIENTRY glProgrbmPbrbmftfr4fNV (GLfnum, GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glProgrbmPbrbmftfr4fvNV (GLfnum, GLuint, donst GLflobt *);
GLAPI void APIENTRY glProgrbmPbrbmftfrs4dvNV (GLfnum, GLuint, GLuint, donst GLdoublf *);
GLAPI void APIENTRY glProgrbmPbrbmftfrs4fvNV (GLfnum, GLuint, GLuint, donst GLflobt *);
GLAPI void APIENTRY glRfqufstRfsidfntProgrbmsNV (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glTrbdkMbtrixNV (GLfnum, GLuint, GLfnum, GLfnum);
GLAPI void APIENTRY glVfrtfxAttribPointfrNV (GLuint, GLint, GLfnum, GLsizfi, donst GLvoid *);
GLAPI void APIENTRY glVfrtfxAttrib1dNV (GLuint, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib1dvNV (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib1fNV (GLuint, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib1fvNV (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib1sNV (GLuint, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib1svNV (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib2dNV (GLuint, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib2dvNV (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib2fNV (GLuint, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib2fvNV (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib2sNV (GLuint, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib2svNV (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib3dNV (GLuint, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib3dvNV (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib3fNV (GLuint, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib3fvNV (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib3sNV (GLuint, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib3svNV (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4dNV (GLuint, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxAttrib4dvNV (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttrib4fNV (GLuint, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxAttrib4fvNV (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttrib4sNV (GLuint, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxAttrib4svNV (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttrib4ubNV (GLuint, GLubytf, GLubytf, GLubytf, GLubytf);
GLAPI void APIENTRY glVfrtfxAttrib4ubvNV (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVfrtfxAttribs1dvNV (GLuint, GLsizfi, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttribs1fvNV (GLuint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttribs1svNV (GLuint, GLsizfi, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttribs2dvNV (GLuint, GLsizfi, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttribs2fvNV (GLuint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttribs2svNV (GLuint, GLsizfi, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttribs3dvNV (GLuint, GLsizfi, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttribs3fvNV (GLuint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttribs3svNV (GLuint, GLsizfi, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttribs4dvNV (GLuint, GLsizfi, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxAttribs4fvNV (GLuint, GLsizfi, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxAttribs4svNV (GLuint, GLsizfi, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxAttribs4ubvNV (GLuint, GLsizfi, donst GLubytf *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLboolfbn (APIENTRYP PFNGLAREPROGRAMSRESIDENTNVPROC) (GLsizfi n, donst GLuint *progrbms, GLboolfbn *rfsidfndfs);
typfdff void (APIENTRYP PFNGLBINDPROGRAMNVPROC) (GLfnum tbrgft, GLuint id);
typfdff void (APIENTRYP PFNGLDELETEPROGRAMSNVPROC) (GLsizfi n, donst GLuint *progrbms);
typfdff void (APIENTRYP PFNGLEXECUTEPROGRAMNVPROC) (GLfnum tbrgft, GLuint id, donst GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGENPROGRAMSNVPROC) (GLsizfi n, GLuint *progrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMPARAMETERDVNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum pnbmf, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMPARAMETERFVNVPROC) (GLfnum tbrgft, GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMIVNVPROC) (GLuint id, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMSTRINGNVPROC) (GLuint id, GLfnum pnbmf, GLubytf *progrbm);
typfdff void (APIENTRYP PFNGLGETTRACKMATRIXIVNVPROC) (GLfnum tbrgft, GLuint bddrfss, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBDVNVPROC) (GLuint indfx, GLfnum pnbmf, GLdoublf *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBFVNVPROC) (GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBIVNVPROC) (GLuint indfx, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBPOINTERVNVPROC) (GLuint indfx, GLfnum pnbmf, GLvoid* *pointfr);
typfdff GLboolfbn (APIENTRYP PFNGLISPROGRAMNVPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLLOADPROGRAMNVPROC) (GLfnum tbrgft, GLuint id, GLsizfi lfn, donst GLubytf *progrbm);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETER4DNVPROC) (GLfnum tbrgft, GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETER4DVNVPROC) (GLfnum tbrgft, GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETER4FNVPROC) (GLfnum tbrgft, GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETER4FVNVPROC) (GLfnum tbrgft, GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETERS4DVNVPROC) (GLfnum tbrgft, GLuint indfx, GLuint dount, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLPROGRAMPARAMETERS4FVNVPROC) (GLfnum tbrgft, GLuint indfx, GLuint dount, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLREQUESTRESIDENTPROGRAMSNVPROC) (GLsizfi n, donst GLuint *progrbms);
typfdff void (APIENTRYP PFNGLTRACKMATRIXNVPROC) (GLfnum tbrgft, GLuint bddrfss, GLfnum mbtrix, GLfnum trbnsform);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBPOINTERNVPROC) (GLuint indfx, GLint fsizf, GLfnum typf, GLsizfi stridf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DNVPROC) (GLuint indfx, GLdoublf x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1DVNVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FNVPROC) (GLuint indfx, GLflobt x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1FVNVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SNVPROC) (GLuint indfx, GLsiort x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1SVNVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DNVPROC) (GLuint indfx, GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2DVNVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FNVPROC) (GLuint indfx, GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2FVNVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SNVPROC) (GLuint indfx, GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2SVNVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DNVPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3DVNVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FNVPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3FVNVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SNVPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3SVNVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DNVPROC) (GLuint indfx, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4DVNVPROC) (GLuint indfx, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FNVPROC) (GLuint indfx, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4FVNVPROC) (GLuint indfx, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SNVPROC) (GLuint indfx, GLsiort x, GLsiort y, GLsiort z, GLsiort w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4SVNVPROC) (GLuint indfx, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UBNVPROC) (GLuint indfx, GLubytf x, GLubytf y, GLubytf z, GLubytf w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4UBVNVPROC) (GLuint indfx, donst GLubytf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS1DVNVPROC) (GLuint indfx, GLsizfi dount, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS1FVNVPROC) (GLuint indfx, GLsizfi dount, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS1SVNVPROC) (GLuint indfx, GLsizfi dount, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS2DVNVPROC) (GLuint indfx, GLsizfi dount, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS2FVNVPROC) (GLuint indfx, GLsizfi dount, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS2SVNVPROC) (GLuint indfx, GLsizfi dount, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS3DVNVPROC) (GLuint indfx, GLsizfi dount, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS3FVNVPROC) (GLuint indfx, GLsizfi dount, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS3SVNVPROC) (GLuint indfx, GLsizfi dount, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS4DVNVPROC) (GLuint indfx, GLsizfi dount, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS4FVNVPROC) (GLuint indfx, GLsizfi dount, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS4SVNVPROC) (GLuint indfx, GLsizfi dount, donst GLsiort *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS4UBVNVPROC) (GLuint indfx, GLsizfi dount, donst GLubytf *v);
#fndif

#ifndff GL_SGIX_tfxturf_doordinbtf_dlbmp
#dffinf GL_SGIX_tfxturf_doordinbtf_dlbmp 1
#fndif

#ifndff GL_SGIX_sdblfbibs_iint
#dffinf GL_SGIX_sdblfbibs_iint 1
#fndif

#ifndff GL_OML_intfrlbdf
#dffinf GL_OML_intfrlbdf 1
#fndif

#ifndff GL_OML_subsbmplf
#dffinf GL_OML_subsbmplf 1
#fndif

#ifndff GL_OML_rfsbmplf
#dffinf GL_OML_rfsbmplf 1
#fndif

#ifndff GL_NV_dopy_dfpti_to_dolor
#dffinf GL_NV_dopy_dfpti_to_dolor 1
#fndif

#ifndff GL_ATI_fnvmbp_bumpmbp
#dffinf GL_ATI_fnvmbp_bumpmbp 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glTfxBumpPbrbmftfrivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glTfxBumpPbrbmftfrfvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glGftTfxBumpPbrbmftfrivATI (GLfnum, GLint *);
GLAPI void APIENTRY glGftTfxBumpPbrbmftfrfvATI (GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLTEXBUMPPARAMETERIVATIPROC) (GLfnum pnbmf, donst GLint *pbrbm);
typfdff void (APIENTRYP PFNGLTEXBUMPPARAMETERFVATIPROC) (GLfnum pnbmf, donst GLflobt *pbrbm);
typfdff void (APIENTRYP PFNGLGETTEXBUMPPARAMETERIVATIPROC) (GLfnum pnbmf, GLint *pbrbm);
typfdff void (APIENTRYP PFNGLGETTEXBUMPPARAMETERFVATIPROC) (GLfnum pnbmf, GLflobt *pbrbm);
#fndif

#ifndff GL_ATI_frbgmfnt_sibdfr
#dffinf GL_ATI_frbgmfnt_sibdfr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLuint APIENTRY glGfnFrbgmfntSibdfrsATI (GLuint);
GLAPI void APIENTRY glBindFrbgmfntSibdfrATI (GLuint);
GLAPI void APIENTRY glDflftfFrbgmfntSibdfrATI (GLuint);
GLAPI void APIENTRY glBfginFrbgmfntSibdfrATI (void);
GLAPI void APIENTRY glEndFrbgmfntSibdfrATI (void);
GLAPI void APIENTRY glPbssTfxCoordATI (GLuint, GLuint, GLfnum);
GLAPI void APIENTRY glSbmplfMbpATI (GLuint, GLuint, GLfnum);
GLAPI void APIENTRY glColorFrbgmfntOp1ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glColorFrbgmfntOp2ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glColorFrbgmfntOp3ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glAlpibFrbgmfntOp1ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glAlpibFrbgmfntOp2ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glAlpibFrbgmfntOp3ATI (GLfnum, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glSftFrbgmfntSibdfrConstbntATI (GLuint, donst GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLuint (APIENTRYP PFNGLGENFRAGMENTSHADERSATIPROC) (GLuint rbngf);
typfdff void (APIENTRYP PFNGLBINDFRAGMENTSHADERATIPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLDELETEFRAGMENTSHADERATIPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLBEGINFRAGMENTSHADERATIPROC) (void);
typfdff void (APIENTRYP PFNGLENDFRAGMENTSHADERATIPROC) (void);
typfdff void (APIENTRYP PFNGLPASSTEXCOORDATIPROC) (GLuint dst, GLuint doord, GLfnum swizzlf);
typfdff void (APIENTRYP PFNGLSAMPLEMAPATIPROC) (GLuint dst, GLuint intfrp, GLfnum swizzlf);
typfdff void (APIENTRYP PFNGLCOLORFRAGMENTOP1ATIPROC) (GLfnum op, GLuint dst, GLuint dstMbsk, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod);
typfdff void (APIENTRYP PFNGLCOLORFRAGMENTOP2ATIPROC) (GLfnum op, GLuint dst, GLuint dstMbsk, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod, GLuint brg2, GLuint brg2Rfp, GLuint brg2Mod);
typfdff void (APIENTRYP PFNGLCOLORFRAGMENTOP3ATIPROC) (GLfnum op, GLuint dst, GLuint dstMbsk, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod, GLuint brg2, GLuint brg2Rfp, GLuint brg2Mod, GLuint brg3, GLuint brg3Rfp, GLuint brg3Mod);
typfdff void (APIENTRYP PFNGLALPHAFRAGMENTOP1ATIPROC) (GLfnum op, GLuint dst, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod);
typfdff void (APIENTRYP PFNGLALPHAFRAGMENTOP2ATIPROC) (GLfnum op, GLuint dst, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod, GLuint brg2, GLuint brg2Rfp, GLuint brg2Mod);
typfdff void (APIENTRYP PFNGLALPHAFRAGMENTOP3ATIPROC) (GLfnum op, GLuint dst, GLuint dstMod, GLuint brg1, GLuint brg1Rfp, GLuint brg1Mod, GLuint brg2, GLuint brg2Rfp, GLuint brg2Mod, GLuint brg3, GLuint brg3Rfp, GLuint brg3Mod);
typfdff void (APIENTRYP PFNGLSETFRAGMENTSHADERCONSTANTATIPROC) (GLuint dst, donst GLflobt *vbluf);
#fndif

#ifndff GL_ATI_pn_tribnglfs
#dffinf GL_ATI_pn_tribnglfs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPNTribnglfsiATI (GLfnum, GLint);
GLAPI void APIENTRY glPNTribnglfsfATI (GLfnum, GLflobt);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPNTRIANGLESIATIPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLPNTRIANGLESFATIPROC) (GLfnum pnbmf, GLflobt pbrbm);
#fndif

#ifndff GL_ATI_vfrtfx_brrby_objfdt
#dffinf GL_ATI_vfrtfx_brrby_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLuint APIENTRY glNfwObjfdtBufffrATI (GLsizfi, donst GLvoid *, GLfnum);
GLAPI GLboolfbn APIENTRY glIsObjfdtBufffrATI (GLuint);
GLAPI void APIENTRY glUpdbtfObjfdtBufffrATI (GLuint, GLuint, GLsizfi, donst GLvoid *, GLfnum);
GLAPI void APIENTRY glGftObjfdtBufffrfvATI (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftObjfdtBufffrivATI (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glFrffObjfdtBufffrATI (GLuint);
GLAPI void APIENTRY glArrbyObjfdtATI (GLfnum, GLint, GLfnum, GLsizfi, GLuint, GLuint);
GLAPI void APIENTRY glGftArrbyObjfdtfvATI (GLfnum, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftArrbyObjfdtivATI (GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glVbribntArrbyObjfdtATI (GLuint, GLfnum, GLsizfi, GLuint, GLuint);
GLAPI void APIENTRY glGftVbribntArrbyObjfdtfvATI (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVbribntArrbyObjfdtivATI (GLuint, GLfnum, GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLuint (APIENTRYP PFNGLNEWOBJECTBUFFERATIPROC) (GLsizfi sizf, donst GLvoid *pointfr, GLfnum usbgf);
typfdff GLboolfbn (APIENTRYP PFNGLISOBJECTBUFFERATIPROC) (GLuint bufffr);
typfdff void (APIENTRYP PFNGLUPDATEOBJECTBUFFERATIPROC) (GLuint bufffr, GLuint offsft, GLsizfi sizf, donst GLvoid *pointfr, GLfnum prfsfrvf);
typfdff void (APIENTRYP PFNGLGETOBJECTBUFFERFVATIPROC) (GLuint bufffr, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETOBJECTBUFFERIVATIPROC) (GLuint bufffr, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLFREEOBJECTBUFFERATIPROC) (GLuint bufffr);
typfdff void (APIENTRYP PFNGLARRAYOBJECTATIPROC) (GLfnum brrby, GLint sizf, GLfnum typf, GLsizfi stridf, GLuint bufffr, GLuint offsft);
typfdff void (APIENTRYP PFNGLGETARRAYOBJECTFVATIPROC) (GLfnum brrby, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETARRAYOBJECTIVATIPROC) (GLfnum brrby, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLVARIANTARRAYOBJECTATIPROC) (GLuint id, GLfnum typf, GLsizfi stridf, GLuint bufffr, GLuint offsft);
typfdff void (APIENTRYP PFNGLGETVARIANTARRAYOBJECTFVATIPROC) (GLuint id, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETVARIANTARRAYOBJECTIVATIPROC) (GLuint id, GLfnum pnbmf, GLint *pbrbms);
#fndif

#ifndff GL_EXT_vfrtfx_sibdfr
#dffinf GL_EXT_vfrtfx_sibdfr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBfginVfrtfxSibdfrEXT (void);
GLAPI void APIENTRY glEndVfrtfxSibdfrEXT (void);
GLAPI void APIENTRY glBindVfrtfxSibdfrEXT (GLuint);
GLAPI GLuint APIENTRY glGfnVfrtfxSibdfrsEXT (GLuint);
GLAPI void APIENTRY glDflftfVfrtfxSibdfrEXT (GLuint);
GLAPI void APIENTRY glSibdfrOp1EXT (GLfnum, GLuint, GLuint);
GLAPI void APIENTRY glSibdfrOp2EXT (GLfnum, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glSibdfrOp3EXT (GLfnum, GLuint, GLuint, GLuint, GLuint);
GLAPI void APIENTRY glSwizzlfEXT (GLuint, GLuint, GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glWritfMbskEXT (GLuint, GLuint, GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glInsfrtComponfntEXT (GLuint, GLuint, GLuint);
GLAPI void APIENTRY glExtrbdtComponfntEXT (GLuint, GLuint, GLuint);
GLAPI GLuint APIENTRY glGfnSymbolsEXT (GLfnum, GLfnum, GLfnum, GLuint);
GLAPI void APIENTRY glSftInvbribntEXT (GLuint, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glSftLodblConstbntEXT (GLuint, GLfnum, donst GLvoid *);
GLAPI void APIENTRY glVbribntbvEXT (GLuint, donst GLbytf *);
GLAPI void APIENTRY glVbribntsvEXT (GLuint, donst GLsiort *);
GLAPI void APIENTRY glVbribntivEXT (GLuint, donst GLint *);
GLAPI void APIENTRY glVbribntfvEXT (GLuint, donst GLflobt *);
GLAPI void APIENTRY glVbribntdvEXT (GLuint, donst GLdoublf *);
GLAPI void APIENTRY glVbribntubvEXT (GLuint, donst GLubytf *);
GLAPI void APIENTRY glVbribntusvEXT (GLuint, donst GLusiort *);
GLAPI void APIENTRY glVbribntuivEXT (GLuint, donst GLuint *);
GLAPI void APIENTRY glVbribntPointfrEXT (GLuint, GLfnum, GLuint, donst GLvoid *);
GLAPI void APIENTRY glEnbblfVbribntClifntStbtfEXT (GLuint);
GLAPI void APIENTRY glDisbblfVbribntClifntStbtfEXT (GLuint);
GLAPI GLuint APIENTRY glBindLigitPbrbmftfrEXT (GLfnum, GLfnum);
GLAPI GLuint APIENTRY glBindMbtfriblPbrbmftfrEXT (GLfnum, GLfnum);
GLAPI GLuint APIENTRY glBindTfxGfnPbrbmftfrEXT (GLfnum, GLfnum, GLfnum);
GLAPI GLuint APIENTRY glBindTfxturfUnitPbrbmftfrEXT (GLfnum, GLfnum);
GLAPI GLuint APIENTRY glBindPbrbmftfrEXT (GLfnum);
GLAPI GLboolfbn APIENTRY glIsVbribntEnbblfdEXT (GLuint, GLfnum);
GLAPI void APIENTRY glGftVbribntBoolfbnvEXT (GLuint, GLfnum, GLboolfbn *);
GLAPI void APIENTRY glGftVbribntIntfgfrvEXT (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftVbribntFlobtvEXT (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVbribntPointfrvEXT (GLuint, GLfnum, GLvoid* *);
GLAPI void APIENTRY glGftInvbribntBoolfbnvEXT (GLuint, GLfnum, GLboolfbn *);
GLAPI void APIENTRY glGftInvbribntIntfgfrvEXT (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftInvbribntFlobtvEXT (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftLodblConstbntBoolfbnvEXT (GLuint, GLfnum, GLboolfbn *);
GLAPI void APIENTRY glGftLodblConstbntIntfgfrvEXT (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftLodblConstbntFlobtvEXT (GLuint, GLfnum, GLflobt *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBEGINVERTEXSHADEREXTPROC) (void);
typfdff void (APIENTRYP PFNGLENDVERTEXSHADEREXTPROC) (void);
typfdff void (APIENTRYP PFNGLBINDVERTEXSHADEREXTPROC) (GLuint id);
typfdff GLuint (APIENTRYP PFNGLGENVERTEXSHADERSEXTPROC) (GLuint rbngf);
typfdff void (APIENTRYP PFNGLDELETEVERTEXSHADEREXTPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLSHADEROP1EXTPROC) (GLfnum op, GLuint rfs, GLuint brg1);
typfdff void (APIENTRYP PFNGLSHADEROP2EXTPROC) (GLfnum op, GLuint rfs, GLuint brg1, GLuint brg2);
typfdff void (APIENTRYP PFNGLSHADEROP3EXTPROC) (GLfnum op, GLuint rfs, GLuint brg1, GLuint brg2, GLuint brg3);
typfdff void (APIENTRYP PFNGLSWIZZLEEXTPROC) (GLuint rfs, GLuint in, GLfnum outX, GLfnum outY, GLfnum outZ, GLfnum outW);
typfdff void (APIENTRYP PFNGLWRITEMASKEXTPROC) (GLuint rfs, GLuint in, GLfnum outX, GLfnum outY, GLfnum outZ, GLfnum outW);
typfdff void (APIENTRYP PFNGLINSERTCOMPONENTEXTPROC) (GLuint rfs, GLuint srd, GLuint num);
typfdff void (APIENTRYP PFNGLEXTRACTCOMPONENTEXTPROC) (GLuint rfs, GLuint srd, GLuint num);
typfdff GLuint (APIENTRYP PFNGLGENSYMBOLSEXTPROC) (GLfnum dbtbtypf, GLfnum storbgftypf, GLfnum rbngf, GLuint domponfnts);
typfdff void (APIENTRYP PFNGLSETINVARIANTEXTPROC) (GLuint id, GLfnum typf, donst GLvoid *bddr);
typfdff void (APIENTRYP PFNGLSETLOCALCONSTANTEXTPROC) (GLuint id, GLfnum typf, donst GLvoid *bddr);
typfdff void (APIENTRYP PFNGLVARIANTBVEXTPROC) (GLuint id, donst GLbytf *bddr);
typfdff void (APIENTRYP PFNGLVARIANTSVEXTPROC) (GLuint id, donst GLsiort *bddr);
typfdff void (APIENTRYP PFNGLVARIANTIVEXTPROC) (GLuint id, donst GLint *bddr);
typfdff void (APIENTRYP PFNGLVARIANTFVEXTPROC) (GLuint id, donst GLflobt *bddr);
typfdff void (APIENTRYP PFNGLVARIANTDVEXTPROC) (GLuint id, donst GLdoublf *bddr);
typfdff void (APIENTRYP PFNGLVARIANTUBVEXTPROC) (GLuint id, donst GLubytf *bddr);
typfdff void (APIENTRYP PFNGLVARIANTUSVEXTPROC) (GLuint id, donst GLusiort *bddr);
typfdff void (APIENTRYP PFNGLVARIANTUIVEXTPROC) (GLuint id, donst GLuint *bddr);
typfdff void (APIENTRYP PFNGLVARIANTPOINTEREXTPROC) (GLuint id, GLfnum typf, GLuint stridf, donst GLvoid *bddr);
typfdff void (APIENTRYP PFNGLENABLEVARIANTCLIENTSTATEEXTPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLDISABLEVARIANTCLIENTSTATEEXTPROC) (GLuint id);
typfdff GLuint (APIENTRYP PFNGLBINDLIGHTPARAMETEREXTPROC) (GLfnum ligit, GLfnum vbluf);
typfdff GLuint (APIENTRYP PFNGLBINDMATERIALPARAMETEREXTPROC) (GLfnum fbdf, GLfnum vbluf);
typfdff GLuint (APIENTRYP PFNGLBINDTEXGENPARAMETEREXTPROC) (GLfnum unit, GLfnum doord, GLfnum vbluf);
typfdff GLuint (APIENTRYP PFNGLBINDTEXTUREUNITPARAMETEREXTPROC) (GLfnum unit, GLfnum vbluf);
typfdff GLuint (APIENTRYP PFNGLBINDPARAMETEREXTPROC) (GLfnum vbluf);
typfdff GLboolfbn (APIENTRYP PFNGLISVARIANTENABLEDEXTPROC) (GLuint id, GLfnum dbp);
typfdff void (APIENTRYP PFNGLGETVARIANTBOOLEANVEXTPROC) (GLuint id, GLfnum vbluf, GLboolfbn *dbtb);
typfdff void (APIENTRYP PFNGLGETVARIANTINTEGERVEXTPROC) (GLuint id, GLfnum vbluf, GLint *dbtb);
typfdff void (APIENTRYP PFNGLGETVARIANTFLOATVEXTPROC) (GLuint id, GLfnum vbluf, GLflobt *dbtb);
typfdff void (APIENTRYP PFNGLGETVARIANTPOINTERVEXTPROC) (GLuint id, GLfnum vbluf, GLvoid* *dbtb);
typfdff void (APIENTRYP PFNGLGETINVARIANTBOOLEANVEXTPROC) (GLuint id, GLfnum vbluf, GLboolfbn *dbtb);
typfdff void (APIENTRYP PFNGLGETINVARIANTINTEGERVEXTPROC) (GLuint id, GLfnum vbluf, GLint *dbtb);
typfdff void (APIENTRYP PFNGLGETINVARIANTFLOATVEXTPROC) (GLuint id, GLfnum vbluf, GLflobt *dbtb);
typfdff void (APIENTRYP PFNGLGETLOCALCONSTANTBOOLEANVEXTPROC) (GLuint id, GLfnum vbluf, GLboolfbn *dbtb);
typfdff void (APIENTRYP PFNGLGETLOCALCONSTANTINTEGERVEXTPROC) (GLuint id, GLfnum vbluf, GLint *dbtb);
typfdff void (APIENTRYP PFNGLGETLOCALCONSTANTFLOATVEXTPROC) (GLuint id, GLfnum vbluf, GLflobt *dbtb);
#fndif

#ifndff GL_ATI_vfrtfx_strfbms
#dffinf GL_ATI_vfrtfx_strfbms 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxStrfbm1sATI (GLfnum, GLsiort);
GLAPI void APIENTRY glVfrtfxStrfbm1svATI (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxStrfbm1iATI (GLfnum, GLint);
GLAPI void APIENTRY glVfrtfxStrfbm1ivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glVfrtfxStrfbm1fATI (GLfnum, GLflobt);
GLAPI void APIENTRY glVfrtfxStrfbm1fvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxStrfbm1dATI (GLfnum, GLdoublf);
GLAPI void APIENTRY glVfrtfxStrfbm1dvATI (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxStrfbm2sATI (GLfnum, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxStrfbm2svATI (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxStrfbm2iATI (GLfnum, GLint, GLint);
GLAPI void APIENTRY glVfrtfxStrfbm2ivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glVfrtfxStrfbm2fATI (GLfnum, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxStrfbm2fvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxStrfbm2dATI (GLfnum, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxStrfbm2dvATI (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxStrfbm3sATI (GLfnum, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxStrfbm3svATI (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxStrfbm3iATI (GLfnum, GLint, GLint, GLint);
GLAPI void APIENTRY glVfrtfxStrfbm3ivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glVfrtfxStrfbm3fATI (GLfnum, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxStrfbm3fvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxStrfbm3dATI (GLfnum, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxStrfbm3dvATI (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glVfrtfxStrfbm4sATI (GLfnum, GLsiort, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glVfrtfxStrfbm4svATI (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glVfrtfxStrfbm4iATI (GLfnum, GLint, GLint, GLint, GLint);
GLAPI void APIENTRY glVfrtfxStrfbm4ivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glVfrtfxStrfbm4fATI (GLfnum, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glVfrtfxStrfbm4fvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glVfrtfxStrfbm4dATI (GLfnum, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glVfrtfxStrfbm4dvATI (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glNormblStrfbm3bATI (GLfnum, GLbytf, GLbytf, GLbytf);
GLAPI void APIENTRY glNormblStrfbm3bvATI (GLfnum, donst GLbytf *);
GLAPI void APIENTRY glNormblStrfbm3sATI (GLfnum, GLsiort, GLsiort, GLsiort);
GLAPI void APIENTRY glNormblStrfbm3svATI (GLfnum, donst GLsiort *);
GLAPI void APIENTRY glNormblStrfbm3iATI (GLfnum, GLint, GLint, GLint);
GLAPI void APIENTRY glNormblStrfbm3ivATI (GLfnum, donst GLint *);
GLAPI void APIENTRY glNormblStrfbm3fATI (GLfnum, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glNormblStrfbm3fvATI (GLfnum, donst GLflobt *);
GLAPI void APIENTRY glNormblStrfbm3dATI (GLfnum, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glNormblStrfbm3dvATI (GLfnum, donst GLdoublf *);
GLAPI void APIENTRY glClifntAdtivfVfrtfxStrfbmATI (GLfnum);
GLAPI void APIENTRY glVfrtfxBlfndEnviATI (GLfnum, GLint);
GLAPI void APIENTRY glVfrtfxBlfndEnvfATI (GLfnum, GLflobt);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1SATIPROC) (GLfnum strfbm, GLsiort x);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1SVATIPROC) (GLfnum strfbm, donst GLsiort *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1IATIPROC) (GLfnum strfbm, GLint x);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1IVATIPROC) (GLfnum strfbm, donst GLint *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1FATIPROC) (GLfnum strfbm, GLflobt x);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1FVATIPROC) (GLfnum strfbm, donst GLflobt *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1DATIPROC) (GLfnum strfbm, GLdoublf x);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM1DVATIPROC) (GLfnum strfbm, donst GLdoublf *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2SATIPROC) (GLfnum strfbm, GLsiort x, GLsiort y);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2SVATIPROC) (GLfnum strfbm, donst GLsiort *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2IATIPROC) (GLfnum strfbm, GLint x, GLint y);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2IVATIPROC) (GLfnum strfbm, donst GLint *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2FATIPROC) (GLfnum strfbm, GLflobt x, GLflobt y);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2FVATIPROC) (GLfnum strfbm, donst GLflobt *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2DATIPROC) (GLfnum strfbm, GLdoublf x, GLdoublf y);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM2DVATIPROC) (GLfnum strfbm, donst GLdoublf *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3SATIPROC) (GLfnum strfbm, GLsiort x, GLsiort y, GLsiort z);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3SVATIPROC) (GLfnum strfbm, donst GLsiort *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3IATIPROC) (GLfnum strfbm, GLint x, GLint y, GLint z);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3IVATIPROC) (GLfnum strfbm, donst GLint *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3FATIPROC) (GLfnum strfbm, GLflobt x, GLflobt y, GLflobt z);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3FVATIPROC) (GLfnum strfbm, donst GLflobt *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3DATIPROC) (GLfnum strfbm, GLdoublf x, GLdoublf y, GLdoublf z);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM3DVATIPROC) (GLfnum strfbm, donst GLdoublf *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4SATIPROC) (GLfnum strfbm, GLsiort x, GLsiort y, GLsiort z, GLsiort w);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4SVATIPROC) (GLfnum strfbm, donst GLsiort *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4IATIPROC) (GLfnum strfbm, GLint x, GLint y, GLint z, GLint w);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4IVATIPROC) (GLfnum strfbm, donst GLint *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4FATIPROC) (GLfnum strfbm, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4FVATIPROC) (GLfnum strfbm, donst GLflobt *doords);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4DATIPROC) (GLfnum strfbm, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLVERTEXSTREAM4DVATIPROC) (GLfnum strfbm, donst GLdoublf *doords);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3BATIPROC) (GLfnum strfbm, GLbytf nx, GLbytf ny, GLbytf nz);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3BVATIPROC) (GLfnum strfbm, donst GLbytf *doords);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3SATIPROC) (GLfnum strfbm, GLsiort nx, GLsiort ny, GLsiort nz);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3SVATIPROC) (GLfnum strfbm, donst GLsiort *doords);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3IATIPROC) (GLfnum strfbm, GLint nx, GLint ny, GLint nz);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3IVATIPROC) (GLfnum strfbm, donst GLint *doords);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3FATIPROC) (GLfnum strfbm, GLflobt nx, GLflobt ny, GLflobt nz);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3FVATIPROC) (GLfnum strfbm, donst GLflobt *doords);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3DATIPROC) (GLfnum strfbm, GLdoublf nx, GLdoublf ny, GLdoublf nz);
typfdff void (APIENTRYP PFNGLNORMALSTREAM3DVATIPROC) (GLfnum strfbm, donst GLdoublf *doords);
typfdff void (APIENTRYP PFNGLCLIENTACTIVEVERTEXSTREAMATIPROC) (GLfnum strfbm);
typfdff void (APIENTRYP PFNGLVERTEXBLENDENVIATIPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLVERTEXBLENDENVFATIPROC) (GLfnum pnbmf, GLflobt pbrbm);
#fndif

#ifndff GL_ATI_flfmfnt_brrby
#dffinf GL_ATI_flfmfnt_brrby 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glElfmfntPointfrATI (GLfnum, donst GLvoid *);
GLAPI void APIENTRY glDrbwElfmfntArrbyATI (GLfnum, GLsizfi);
GLAPI void APIENTRY glDrbwRbngfElfmfntArrbyATI (GLfnum, GLuint, GLuint, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLELEMENTPOINTERATIPROC) (GLfnum typf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLDRAWELEMENTARRAYATIPROC) (GLfnum modf, GLsizfi dount);
typfdff void (APIENTRYP PFNGLDRAWRANGEELEMENTARRAYATIPROC) (GLfnum modf, GLuint stbrt, GLuint fnd, GLsizfi dount);
#fndif

#ifndff GL_SUN_mfsi_brrby
#dffinf GL_SUN_mfsi_brrby 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDrbwMfsiArrbysSUN (GLfnum, GLint, GLsizfi, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDRAWMESHARRAYSSUNPROC) (GLfnum modf, GLint first, GLsizfi dount, GLsizfi widti);
#fndif

#ifndff GL_SUN_slidf_bddum
#dffinf GL_SUN_slidf_bddum 1
#fndif

#ifndff GL_NV_multisbmplf_filtfr_iint
#dffinf GL_NV_multisbmplf_filtfr_iint 1
#fndif

#ifndff GL_NV_dfpti_dlbmp
#dffinf GL_NV_dfpti_dlbmp 1
#fndif

#ifndff GL_NV_oddlusion_qufry
#dffinf GL_NV_oddlusion_qufry 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGfnOddlusionQufrifsNV (GLsizfi, GLuint *);
GLAPI void APIENTRY glDflftfOddlusionQufrifsNV (GLsizfi, donst GLuint *);
GLAPI GLboolfbn APIENTRY glIsOddlusionQufryNV (GLuint);
GLAPI void APIENTRY glBfginOddlusionQufryNV (GLuint);
GLAPI void APIENTRY glEndOddlusionQufryNV (void);
GLAPI void APIENTRY glGftOddlusionQufryivNV (GLuint, GLfnum, GLint *);
GLAPI void APIENTRY glGftOddlusionQufryuivNV (GLuint, GLfnum, GLuint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGENOCCLUSIONQUERIESNVPROC) (GLsizfi n, GLuint *ids);
typfdff void (APIENTRYP PFNGLDELETEOCCLUSIONQUERIESNVPROC) (GLsizfi n, donst GLuint *ids);
typfdff GLboolfbn (APIENTRYP PFNGLISOCCLUSIONQUERYNVPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLBEGINOCCLUSIONQUERYNVPROC) (GLuint id);
typfdff void (APIENTRYP PFNGLENDOCCLUSIONQUERYNVPROC) (void);
typfdff void (APIENTRYP PFNGLGETOCCLUSIONQUERYIVNVPROC) (GLuint id, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGETOCCLUSIONQUERYUIVNVPROC) (GLuint id, GLfnum pnbmf, GLuint *pbrbms);
#fndif

#ifndff GL_NV_point_spritf
#dffinf GL_NV_point_spritf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPointPbrbmftfriNV (GLfnum, GLint);
GLAPI void APIENTRY glPointPbrbmftfrivNV (GLfnum, donst GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPOINTPARAMETERINVPROC) (GLfnum pnbmf, GLint pbrbm);
typfdff void (APIENTRYP PFNGLPOINTPARAMETERIVNVPROC) (GLfnum pnbmf, donst GLint *pbrbms);
#fndif

#ifndff GL_NV_tfxturf_sibdfr3
#dffinf GL_NV_tfxturf_sibdfr3 1
#fndif

#ifndff GL_NV_vfrtfx_progrbm1_1
#dffinf GL_NV_vfrtfx_progrbm1_1 1
#fndif

#ifndff GL_EXT_sibdow_funds
#dffinf GL_EXT_sibdow_funds 1
#fndif

#ifndff GL_EXT_stfndil_two_sidf
#dffinf GL_EXT_stfndil_two_sidf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glAdtivfStfndilFbdfEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLACTIVESTENCILFACEEXTPROC) (GLfnum fbdf);
#fndif

#ifndff GL_ATI_tfxt_frbgmfnt_sibdfr
#dffinf GL_ATI_tfxt_frbgmfnt_sibdfr 1
#fndif

#ifndff GL_APPLE_dlifnt_storbgf
#dffinf GL_APPLE_dlifnt_storbgf 1
#fndif

#ifndff GL_APPLE_flfmfnt_brrby
#dffinf GL_APPLE_flfmfnt_brrby 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glElfmfntPointfrAPPLE (GLfnum, donst GLvoid *);
GLAPI void APIENTRY glDrbwElfmfntArrbyAPPLE (GLfnum, GLint, GLsizfi);
GLAPI void APIENTRY glDrbwRbngfElfmfntArrbyAPPLE (GLfnum, GLuint, GLuint, GLint, GLsizfi);
GLAPI void APIENTRY glMultiDrbwElfmfntArrbyAPPLE (GLfnum, donst GLint *, donst GLsizfi *, GLsizfi);
GLAPI void APIENTRY glMultiDrbwRbngfElfmfntArrbyAPPLE (GLfnum, GLuint, GLuint, donst GLint *, donst GLsizfi *, GLsizfi);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLELEMENTPOINTERAPPLEPROC) (GLfnum typf, donst GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLDRAWELEMENTARRAYAPPLEPROC) (GLfnum modf, GLint first, GLsizfi dount);
typfdff void (APIENTRYP PFNGLDRAWRANGEELEMENTARRAYAPPLEPROC) (GLfnum modf, GLuint stbrt, GLuint fnd, GLint first, GLsizfi dount);
typfdff void (APIENTRYP PFNGLMULTIDRAWELEMENTARRAYAPPLEPROC) (GLfnum modf, donst GLint *first, donst GLsizfi *dount, GLsizfi primdount);
typfdff void (APIENTRYP PFNGLMULTIDRAWRANGEELEMENTARRAYAPPLEPROC) (GLfnum modf, GLuint stbrt, GLuint fnd, donst GLint *first, donst GLsizfi *dount, GLsizfi primdount);
#fndif

#ifndff GL_APPLE_ffndf
#dffinf GL_APPLE_ffndf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glGfnFfndfsAPPLE (GLsizfi, GLuint *);
GLAPI void APIENTRY glDflftfFfndfsAPPLE (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glSftFfndfAPPLE (GLuint);
GLAPI GLboolfbn APIENTRY glIsFfndfAPPLE (GLuint);
GLAPI GLboolfbn APIENTRY glTfstFfndfAPPLE (GLuint);
GLAPI void APIENTRY glFinisiFfndfAPPLE (GLuint);
GLAPI GLboolfbn APIENTRY glTfstObjfdtAPPLE (GLfnum, GLuint);
GLAPI void APIENTRY glFinisiObjfdtAPPLE (GLfnum, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLGENFENCESAPPLEPROC) (GLsizfi n, GLuint *ffndfs);
typfdff void (APIENTRYP PFNGLDELETEFENCESAPPLEPROC) (GLsizfi n, donst GLuint *ffndfs);
typfdff void (APIENTRYP PFNGLSETFENCEAPPLEPROC) (GLuint ffndf);
typfdff GLboolfbn (APIENTRYP PFNGLISFENCEAPPLEPROC) (GLuint ffndf);
typfdff GLboolfbn (APIENTRYP PFNGLTESTFENCEAPPLEPROC) (GLuint ffndf);
typfdff void (APIENTRYP PFNGLFINISHFENCEAPPLEPROC) (GLuint ffndf);
typfdff GLboolfbn (APIENTRYP PFNGLTESTOBJECTAPPLEPROC) (GLfnum objfdt, GLuint nbmf);
typfdff void (APIENTRYP PFNGLFINISHOBJECTAPPLEPROC) (GLfnum objfdt, GLint nbmf);
#fndif

#ifndff GL_APPLE_vfrtfx_brrby_objfdt
#dffinf GL_APPLE_vfrtfx_brrby_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBindVfrtfxArrbyAPPLE (GLuint);
GLAPI void APIENTRY glDflftfVfrtfxArrbysAPPLE (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnVfrtfxArrbysAPPLE (GLsizfi, donst GLuint *);
GLAPI GLboolfbn APIENTRY glIsVfrtfxArrbyAPPLE (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBINDVERTEXARRAYAPPLEPROC) (GLuint brrby);
typfdff void (APIENTRYP PFNGLDELETEVERTEXARRAYSAPPLEPROC) (GLsizfi n, donst GLuint *brrbys);
typfdff void (APIENTRYP PFNGLGENVERTEXARRAYSAPPLEPROC) (GLsizfi n, donst GLuint *brrbys);
typfdff GLboolfbn (APIENTRYP PFNGLISVERTEXARRAYAPPLEPROC) (GLuint brrby);
#fndif

#ifndff GL_APPLE_vfrtfx_brrby_rbngf
#dffinf GL_APPLE_vfrtfx_brrby_rbngf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxArrbyRbngfAPPLE (GLsizfi, GLvoid *);
GLAPI void APIENTRY glFlusiVfrtfxArrbyRbngfAPPLE (GLsizfi, GLvoid *);
GLAPI void APIENTRY glVfrtfxArrbyPbrbmftfriAPPLE (GLfnum, GLint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXARRAYRANGEAPPLEPROC) (GLsizfi lfngti, GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLFLUSHVERTEXARRAYRANGEAPPLEPROC) (GLsizfi lfngti, GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLVERTEXARRAYPARAMETERIAPPLEPROC) (GLfnum pnbmf, GLint pbrbm);
#fndif

#ifndff GL_APPLE_ydbdr_422
#dffinf GL_APPLE_ydbdr_422 1
#fndif

#ifndff GL_S3_s3td
#dffinf GL_S3_s3td 1
#fndif

#ifndff GL_ATI_drbw_bufffrs
#dffinf GL_ATI_drbw_bufffrs 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDrbwBufffrsATI (GLsizfi, donst GLfnum *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDRAWBUFFERSATIPROC) (GLsizfi n, donst GLfnum *bufs);
#fndif

#ifndff GL_ATI_pixfl_formbt_flobt
#dffinf GL_ATI_pixfl_formbt_flobt 1
/* Tiis is rfblly b WGL fxtfnsion, but dffinfs somf bssodibtfd GL fnums.
 * ATI dofs not fxport "GL_ATI_pixfl_formbt_flobt" in tif GL_EXTENSIONS string.
 */
#fndif

#ifndff GL_ATI_tfxturf_fnv_dombinf3
#dffinf GL_ATI_tfxturf_fnv_dombinf3 1
#fndif

#ifndff GL_ATI_tfxturf_flobt
#dffinf GL_ATI_tfxturf_flobt 1
#fndif

#ifndff GL_NV_flobt_bufffr
#dffinf GL_NV_flobt_bufffr 1
#fndif

#ifndff GL_NV_frbgmfnt_progrbm
#dffinf GL_NV_frbgmfnt_progrbm 1
/* Somf NV_frbgmfnt_progrbm fntry points brf sibrfd witi ARB_vfrtfx_progrbm. */
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glProgrbmNbmfdPbrbmftfr4fNV (GLuint, GLsizfi, donst GLubytf *, GLflobt, GLflobt, GLflobt, GLflobt);
GLAPI void APIENTRY glProgrbmNbmfdPbrbmftfr4dNV (GLuint, GLsizfi, donst GLubytf *, GLdoublf, GLdoublf, GLdoublf, GLdoublf);
GLAPI void APIENTRY glProgrbmNbmfdPbrbmftfr4fvNV (GLuint, GLsizfi, donst GLubytf *, donst GLflobt *);
GLAPI void APIENTRY glProgrbmNbmfdPbrbmftfr4dvNV (GLuint, GLsizfi, donst GLubytf *, donst GLdoublf *);
GLAPI void APIENTRY glGftProgrbmNbmfdPbrbmftfrfvNV (GLuint, GLsizfi, donst GLubytf *, GLflobt *);
GLAPI void APIENTRY glGftProgrbmNbmfdPbrbmftfrdvNV (GLuint, GLsizfi, donst GLubytf *, GLdoublf *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPROGRAMNAMEDPARAMETER4FNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, GLflobt x, GLflobt y, GLflobt z, GLflobt w);
typfdff void (APIENTRYP PFNGLPROGRAMNAMEDPARAMETER4DNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, GLdoublf x, GLdoublf y, GLdoublf z, GLdoublf w);
typfdff void (APIENTRYP PFNGLPROGRAMNAMEDPARAMETER4FVNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, donst GLflobt *v);
typfdff void (APIENTRYP PFNGLPROGRAMNAMEDPARAMETER4DVNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, donst GLdoublf *v);
typfdff void (APIENTRYP PFNGLGETPROGRAMNAMEDPARAMETERFVNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETPROGRAMNAMEDPARAMETERDVNVPROC) (GLuint id, GLsizfi lfn, donst GLubytf *nbmf, GLdoublf *pbrbms);
#fndif

#ifndff GL_NV_iblf_flobt
#dffinf GL_NV_iblf_flobt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfx2iNV (GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfx2ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfx3iNV (GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfx3ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfx4iNV (GLiblfNV, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfx4ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glNormbl3iNV (GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glNormbl3ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glColor3iNV (GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glColor3ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glColor4iNV (GLiblfNV, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glColor4ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glTfxCoord1iNV (GLiblfNV);
GLAPI void APIENTRY glTfxCoord1ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glTfxCoord2iNV (GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glTfxCoord2ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glTfxCoord3iNV (GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glTfxCoord3ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glTfxCoord4iNV (GLiblfNV, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glTfxCoord4ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glMultiTfxCoord1iNV (GLfnum, GLiblfNV);
GLAPI void APIENTRY glMultiTfxCoord1ivNV (GLfnum, donst GLiblfNV *);
GLAPI void APIENTRY glMultiTfxCoord2iNV (GLfnum, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glMultiTfxCoord2ivNV (GLfnum, donst GLiblfNV *);
GLAPI void APIENTRY glMultiTfxCoord3iNV (GLfnum, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glMultiTfxCoord3ivNV (GLfnum, donst GLiblfNV *);
GLAPI void APIENTRY glMultiTfxCoord4iNV (GLfnum, GLiblfNV, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glMultiTfxCoord4ivNV (GLfnum, donst GLiblfNV *);
GLAPI void APIENTRY glFogCoordiNV (GLiblfNV);
GLAPI void APIENTRY glFogCoordivNV (donst GLiblfNV *);
GLAPI void APIENTRY glSfdondbryColor3iNV (GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glSfdondbryColor3ivNV (donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxWfigitiNV (GLiblfNV);
GLAPI void APIENTRY glVfrtfxWfigitivNV (donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttrib1iNV (GLuint, GLiblfNV);
GLAPI void APIENTRY glVfrtfxAttrib1ivNV (GLuint, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttrib2iNV (GLuint, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfxAttrib2ivNV (GLuint, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttrib3iNV (GLuint, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfxAttrib3ivNV (GLuint, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttrib4iNV (GLuint, GLiblfNV, GLiblfNV, GLiblfNV, GLiblfNV);
GLAPI void APIENTRY glVfrtfxAttrib4ivNV (GLuint, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttribs1ivNV (GLuint, GLsizfi, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttribs2ivNV (GLuint, GLsizfi, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttribs3ivNV (GLuint, GLsizfi, donst GLiblfNV *);
GLAPI void APIENTRY glVfrtfxAttribs4ivNV (GLuint, GLsizfi, donst GLiblfNV *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEX2HNVPROC) (GLiblfNV x, GLiblfNV y);
typfdff void (APIENTRYP PFNGLVERTEX2HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEX3HNVPROC) (GLiblfNV x, GLiblfNV y, GLiblfNV z);
typfdff void (APIENTRYP PFNGLVERTEX3HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEX4HNVPROC) (GLiblfNV x, GLiblfNV y, GLiblfNV z, GLiblfNV w);
typfdff void (APIENTRYP PFNGLVERTEX4HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLNORMAL3HNVPROC) (GLiblfNV nx, GLiblfNV ny, GLiblfNV nz);
typfdff void (APIENTRYP PFNGLNORMAL3HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLCOLOR3HNVPROC) (GLiblfNV rfd, GLiblfNV grffn, GLiblfNV bluf);
typfdff void (APIENTRYP PFNGLCOLOR3HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLCOLOR4HNVPROC) (GLiblfNV rfd, GLiblfNV grffn, GLiblfNV bluf, GLiblfNV blpib);
typfdff void (APIENTRYP PFNGLCOLOR4HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLTEXCOORD1HNVPROC) (GLiblfNV s);
typfdff void (APIENTRYP PFNGLTEXCOORD1HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLTEXCOORD2HNVPROC) (GLiblfNV s, GLiblfNV t);
typfdff void (APIENTRYP PFNGLTEXCOORD2HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLTEXCOORD3HNVPROC) (GLiblfNV s, GLiblfNV t, GLiblfNV r);
typfdff void (APIENTRYP PFNGLTEXCOORD3HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLTEXCOORD4HNVPROC) (GLiblfNV s, GLiblfNV t, GLiblfNV r, GLiblfNV q);
typfdff void (APIENTRYP PFNGLTEXCOORD4HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1HNVPROC) (GLfnum tbrgft, GLiblfNV s);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD1HVNVPROC) (GLfnum tbrgft, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2HNVPROC) (GLfnum tbrgft, GLiblfNV s, GLiblfNV t);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD2HVNVPROC) (GLfnum tbrgft, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3HNVPROC) (GLfnum tbrgft, GLiblfNV s, GLiblfNV t, GLiblfNV r);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD3HVNVPROC) (GLfnum tbrgft, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4HNVPROC) (GLfnum tbrgft, GLiblfNV s, GLiblfNV t, GLiblfNV r, GLiblfNV q);
typfdff void (APIENTRYP PFNGLMULTITEXCOORD4HVNVPROC) (GLfnum tbrgft, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLFOGCOORDHNVPROC) (GLiblfNV fog);
typfdff void (APIENTRYP PFNGLFOGCOORDHVNVPROC) (donst GLiblfNV *fog);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3HNVPROC) (GLiblfNV rfd, GLiblfNV grffn, GLiblfNV bluf);
typfdff void (APIENTRYP PFNGLSECONDARYCOLOR3HVNVPROC) (donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXWEIGHTHNVPROC) (GLiblfNV wfigit);
typfdff void (APIENTRYP PFNGLVERTEXWEIGHTHVNVPROC) (donst GLiblfNV *wfigit);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1HNVPROC) (GLuint indfx, GLiblfNV x);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB1HVNVPROC) (GLuint indfx, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2HNVPROC) (GLuint indfx, GLiblfNV x, GLiblfNV y);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB2HVNVPROC) (GLuint indfx, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3HNVPROC) (GLuint indfx, GLiblfNV x, GLiblfNV y, GLiblfNV z);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB3HVNVPROC) (GLuint indfx, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4HNVPROC) (GLuint indfx, GLiblfNV x, GLiblfNV y, GLiblfNV z, GLiblfNV w);
typfdff void (APIENTRYP PFNGLVERTEXATTRIB4HVNVPROC) (GLuint indfx, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS1HVNVPROC) (GLuint indfx, GLsizfi n, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS2HVNVPROC) (GLuint indfx, GLsizfi n, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS3HVNVPROC) (GLuint indfx, GLsizfi n, donst GLiblfNV *v);
typfdff void (APIENTRYP PFNGLVERTEXATTRIBS4HVNVPROC) (GLuint indfx, GLsizfi n, donst GLiblfNV *v);
#fndif

#ifndff GL_NV_pixfl_dbtb_rbngf
#dffinf GL_NV_pixfl_dbtb_rbngf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPixflDbtbRbngfNV (GLfnum, GLsizfi, GLvoid *);
GLAPI void APIENTRY glFlusiPixflDbtbRbngfNV (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPIXELDATARANGENVPROC) (GLfnum tbrgft, GLsizfi lfngti, GLvoid *pointfr);
typfdff void (APIENTRYP PFNGLFLUSHPIXELDATARANGENVPROC) (GLfnum tbrgft);
#fndif

#ifndff GL_NV_primitivf_rfstbrt
#dffinf GL_NV_primitivf_rfstbrt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glPrimitivfRfstbrtNV (void);
GLAPI void APIENTRY glPrimitivfRfstbrtIndfxNV (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLPRIMITIVERESTARTNVPROC) (void);
typfdff void (APIENTRYP PFNGLPRIMITIVERESTARTINDEXNVPROC) (GLuint indfx);
#fndif

#ifndff GL_NV_tfxturf_fxpbnd_normbl
#dffinf GL_NV_tfxturf_fxpbnd_normbl 1
#fndif

#ifndff GL_NV_vfrtfx_progrbm2
#dffinf GL_NV_vfrtfx_progrbm2 1
#fndif

#ifndff GL_ATI_mbp_objfdt_bufffr
#dffinf GL_ATI_mbp_objfdt_bufffr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLvoid* APIENTRY glMbpObjfdtBufffrATI (GLuint);
GLAPI void APIENTRY glUnmbpObjfdtBufffrATI (GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLvoid* (APIENTRYP PFNGLMAPOBJECTBUFFERATIPROC) (GLuint bufffr);
typfdff void (APIENTRYP PFNGLUNMAPOBJECTBUFFERATIPROC) (GLuint bufffr);
#fndif

#ifndff GL_ATI_sfpbrbtf_stfndil
#dffinf GL_ATI_sfpbrbtf_stfndil 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glStfndilOpSfpbrbtfATI (GLfnum, GLfnum, GLfnum, GLfnum);
GLAPI void APIENTRY glStfndilFundSfpbrbtfATI (GLfnum, GLfnum, GLint, GLuint);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSTENCILOPSEPARATEATIPROC) (GLfnum fbdf, GLfnum sfbil, GLfnum dpfbil, GLfnum dppbss);
typfdff void (APIENTRYP PFNGLSTENCILFUNCSEPARATEATIPROC) (GLfnum frontfund, GLfnum bbdkfund, GLint rff, GLuint mbsk);
#fndif

#ifndff GL_ATI_vfrtfx_bttrib_brrby_objfdt
#dffinf GL_ATI_vfrtfx_bttrib_brrby_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glVfrtfxAttribArrbyObjfdtATI (GLuint, GLint, GLfnum, GLboolfbn, GLsizfi, GLuint, GLuint);
GLAPI void APIENTRY glGftVfrtfxAttribArrbyObjfdtfvATI (GLuint, GLfnum, GLflobt *);
GLAPI void APIENTRY glGftVfrtfxAttribArrbyObjfdtivATI (GLuint, GLfnum, GLint *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLVERTEXATTRIBARRAYOBJECTATIPROC) (GLuint indfx, GLint sizf, GLfnum typf, GLboolfbn normblizfd, GLsizfi stridf, GLuint bufffr, GLuint offsft);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBARRAYOBJECTFVATIPROC) (GLuint indfx, GLfnum pnbmf, GLflobt *pbrbms);
typfdff void (APIENTRYP PFNGLGETVERTEXATTRIBARRAYOBJECTIVATIPROC) (GLuint indfx, GLfnum pnbmf, GLint *pbrbms);
#fndif

#ifndff GL_OES_rfbd_formbt
#dffinf GL_OES_rfbd_formbt 1
#fndif

#ifndff GL_EXT_dfpti_bounds_tfst
#dffinf GL_EXT_dfpti_bounds_tfst 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glDfptiBoundsEXT (GLdlbmpd, GLdlbmpd);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLDEPTHBOUNDSEXTPROC) (GLdlbmpd zmin, GLdlbmpd zmbx);
#fndif

#ifndff GL_EXT_tfxturf_mirror_dlbmp
#dffinf GL_EXT_tfxturf_mirror_dlbmp 1
#fndif

#ifndff GL_EXT_blfnd_fqubtion_sfpbrbtf
#dffinf GL_EXT_blfnd_fqubtion_sfpbrbtf 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glBlfndEqubtionSfpbrbtfEXT (GLfnum, GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLBLENDEQUATIONSEPARATEEXTPROC) (GLfnum modfRGB, GLfnum modfAlpib);
#fndif

#ifndff GL_MESA_pbdk_invfrt
#dffinf GL_MESA_pbdk_invfrt 1
#fndif

#ifndff GL_MESA_ydbdr_tfxturf
#dffinf GL_MESA_ydbdr_tfxturf 1
#fndif

#ifndff GL_EXT_pixfl_bufffr_objfdt
#dffinf GL_EXT_pixfl_bufffr_objfdt 1
#fndif

#ifndff GL_NV_frbgmfnt_progrbm_option
#dffinf GL_NV_frbgmfnt_progrbm_option 1
#fndif

#ifndff GL_NV_frbgmfnt_progrbm2
#dffinf GL_NV_frbgmfnt_progrbm2 1
#fndif

#ifndff GL_NV_vfrtfx_progrbm2_option
#dffinf GL_NV_vfrtfx_progrbm2_option 1
#fndif

#ifndff GL_NV_vfrtfx_progrbm3
#dffinf GL_NV_vfrtfx_progrbm3 1
#fndif

#ifndff GL_EXT_frbmfbufffr_objfdt
#dffinf GL_EXT_frbmfbufffr_objfdt 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI GLboolfbn APIENTRY glIsRfndfrbufffrEXT (GLuint);
GLAPI void APIENTRY glBindRfndfrbufffrEXT (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfRfndfrbufffrsEXT (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnRfndfrbufffrsEXT (GLsizfi, GLuint *);
GLAPI void APIENTRY glRfndfrbufffrStorbgfEXT (GLfnum, GLfnum, GLsizfi, GLsizfi);
GLAPI void APIENTRY glGftRfndfrbufffrPbrbmftfrivEXT (GLfnum, GLfnum, GLint *);
GLAPI GLboolfbn APIENTRY glIsFrbmfbufffrEXT (GLuint);
GLAPI void APIENTRY glBindFrbmfbufffrEXT (GLfnum, GLuint);
GLAPI void APIENTRY glDflftfFrbmfbufffrsEXT (GLsizfi, donst GLuint *);
GLAPI void APIENTRY glGfnFrbmfbufffrsEXT (GLsizfi, GLuint *);
GLAPI GLfnum APIENTRY glCifdkFrbmfbufffrStbtusEXT (GLfnum);
GLAPI void APIENTRY glFrbmfbufffrTfxturf1DEXT (GLfnum, GLfnum, GLfnum, GLuint, GLint);
GLAPI void APIENTRY glFrbmfbufffrTfxturf2DEXT (GLfnum, GLfnum, GLfnum, GLuint, GLint);
GLAPI void APIENTRY glFrbmfbufffrTfxturf3DEXT (GLfnum, GLfnum, GLfnum, GLuint, GLint, GLint);
GLAPI void APIENTRY glFrbmfbufffrRfndfrbufffrEXT (GLfnum, GLfnum, GLfnum, GLuint);
GLAPI void APIENTRY glGftFrbmfbufffrAttbdimfntPbrbmftfrivEXT (GLfnum, GLfnum, GLfnum, GLint *);
GLAPI void APIENTRY glGfnfrbtfMipmbpEXT (GLfnum);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff GLboolfbn (APIENTRYP PFNGLISRENDERBUFFEREXTPROC) (GLuint rfndfrbufffr);
typfdff void (APIENTRYP PFNGLBINDRENDERBUFFEREXTPROC) (GLfnum tbrgft, GLuint rfndfrbufffr);
typfdff void (APIENTRYP PFNGLDELETERENDERBUFFERSEXTPROC) (GLsizfi n, donst GLuint *rfndfrbufffrs);
typfdff void (APIENTRYP PFNGLGENRENDERBUFFERSEXTPROC) (GLsizfi n, GLuint *rfndfrbufffrs);
typfdff void (APIENTRYP PFNGLRENDERBUFFERSTORAGEEXTPROC) (GLfnum tbrgft, GLfnum intfrnblformbt, GLsizfi widti, GLsizfi ifigit);
typfdff void (APIENTRYP PFNGLGETRENDERBUFFERPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum pnbmf, GLint *pbrbms);
typfdff GLboolfbn (APIENTRYP PFNGLISFRAMEBUFFEREXTPROC) (GLuint frbmfbufffr);
typfdff void (APIENTRYP PFNGLBINDFRAMEBUFFEREXTPROC) (GLfnum tbrgft, GLuint frbmfbufffr);
typfdff void (APIENTRYP PFNGLDELETEFRAMEBUFFERSEXTPROC) (GLsizfi n, donst GLuint *frbmfbufffrs);
typfdff void (APIENTRYP PFNGLGENFRAMEBUFFERSEXTPROC) (GLsizfi n, GLuint *frbmfbufffrs);
typfdff GLfnum (APIENTRYP PFNGLCHECKFRAMEBUFFERSTATUSEXTPROC) (GLfnum tbrgft);
typfdff void (APIENTRYP PFNGLFRAMEBUFFERTEXTURE1DEXTPROC) (GLfnum tbrgft, GLfnum bttbdimfnt, GLfnum tfxtbrgft, GLuint tfxturf, GLint lfvfl);
typfdff void (APIENTRYP PFNGLFRAMEBUFFERTEXTURE2DEXTPROC) (GLfnum tbrgft, GLfnum bttbdimfnt, GLfnum tfxtbrgft, GLuint tfxturf, GLint lfvfl);
typfdff void (APIENTRYP PFNGLFRAMEBUFFERTEXTURE3DEXTPROC) (GLfnum tbrgft, GLfnum bttbdimfnt, GLfnum tfxtbrgft, GLuint tfxturf, GLint lfvfl, GLint zoffsft);
typfdff void (APIENTRYP PFNGLFRAMEBUFFERRENDERBUFFEREXTPROC) (GLfnum tbrgft, GLfnum bttbdimfnt, GLfnum rfndfrbufffrtbrgft, GLuint rfndfrbufffr);
typfdff void (APIENTRYP PFNGLGETFRAMEBUFFERATTACHMENTPARAMETERIVEXTPROC) (GLfnum tbrgft, GLfnum bttbdimfnt, GLfnum pnbmf, GLint *pbrbms);
typfdff void (APIENTRYP PFNGLGENERATEMIPMAPEXTPROC) (GLfnum tbrgft);
#fndif

#ifndff GL_GREMEDY_string_mbrkfr
#dffinf GL_GREMEDY_string_mbrkfr 1
#ifdff GL_GLEXT_PROTOTYPES
GLAPI void APIENTRY glStringMbrkfrGREMEDY (GLsizfi, donst GLvoid *);
#fndif /* GL_GLEXT_PROTOTYPES */
typfdff void (APIENTRYP PFNGLSTRINGMARKERGREMEDYPROC) (GLsizfi lfn, donst GLvoid *string);
#fndif


#ifdff __dplusplus
}
#fndif

#fndif
