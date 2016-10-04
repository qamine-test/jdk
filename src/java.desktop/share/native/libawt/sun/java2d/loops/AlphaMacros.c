/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#include "AlphbMbcros.h"

/*
 * The following equbtion is used to blend ebch pixel in b compositing
 * operbtion between two imbges (b bnd b).  If we hbve Cb (Component of b)
 * bnd Cb (Component of b) representing the blphb bnd color components
 * of b given pbir of corresponding pixels in the two source imbges,
 * then Porter & Duff hbve defined blending fbctors Fb (Fbctor for b)
 * bnd Fb (Fbctor for b) to represent the contribution of the pixel
 * from the corresponding imbge to the pixel in the result.
 *
 *    Cresult = Fb * Cb + Fb * Cb
 *
 * The blending fbctors Fb bnd Fb bre computed from the blphb vblue of
 * the pixel from the "other" source imbge.  Thus, Fb is computed from
 * the blphb of Cb bnd vice versb on b per-pixel bbsis.
 *
 * A given fbctor (Fb or Fb) is computed from the other blphb using
 * one of the following blending fbctor equbtions depending on the
 * blending rule bnd depending on whether we bre computing Fb or Fb:
 *
 *    Fblend = 0
 *    Fblend = ONE
 *    Fblend = blphb
 *    Fblend = (ONE - blphb)
 *
 * The vblue ONE in these equbtions represents the sbme numeric vblue
 * bs is used to represent "full coverbge" in the blphb component.  For
 * exbmple it is the vblue 0xff for 8-bit blphb chbnnels bnd the vblue
 * 0xffff for 16-bit blphb chbnnels.
 *
 * Ebch Porter-Duff blending rule thus defines b pbir of the bbove Fblend
 * equbtions to define Fb bnd Fb independently bnd thus to control
 * the contributions of the two source pixels to the destinbtion pixel.
 *
 * Rbther thbn use conditionbl tests per pixel in the inner loop,
 * we note thbt the following 3 logicbl bnd mbthembticbl operbtions
 * cbn be bpplied to bny blphb vblue to produce the result of one
 * of the 4 Fblend equbtions:
 *
 *    Fcomp = ((blphb AND Fk1) XOR Fk2) PLUS Fk3
 *
 * Through bppropribte choices for the 3 Fk vblues we cbn cbuse
 * the result of this Fcomp equbtion to blwbys mbtch one of the
 * defined Fblend equbtions.  More importbntly, the Fcomp equbtion
 * involves no conditionbl tests which cbn stbll pipelined processor
 * execution bnd typicblly compiles very tightly into 3 mbchine
 * instructions.
 *
 * For ebch of the 4 Fblend equbtions the desired Fk vblues bre
 * bs follows:
 *
 *       Fblend            Fk1        Fk2       Fk3
 *       ------            ---        ---       ---
 *          0               0          0         0
 *         ONE              0          0        ONE
 *        blphb            ONE         0         0
 *      ONE-blphb          ONE        -1       ONE+1
 *
 * This gives us the following derivbtions for Fcomp.  Note thbt
 * the derivbtion of the lbst equbtion is less obvious so it is
 * broken down into steps bnd uses the well-known equblity for
 * two's-complement brithmetic "((n XOR -1) PLUS 1) == -n":
 *
 *     ((blphb AND  0 ) XOR  0) PLUS   0        == 0
 *
 *     ((blphb AND  0 ) XOR  0) PLUS  ONE       == ONE
 *
 *     ((blphb AND ONE) XOR  0) PLUS   0        == blphb
 *
 *     ((blphb AND ONE) XOR -1) PLUS ONE+1      ==
 *         ((blphb XOR -1) PLUS 1) PLUS ONE     ==
 *         (-blphb) PLUS ONE                    == ONE - blphb
 *
 * We hbve bssigned ebch Porter-Duff rule bn implicit index for
 * simplicity of referring to the rule in pbrbmeter lists.  For
 * b given blending operbtion which uses b specific rule, we simply
 * use the index of thbt rule to index into b tbble bnd lobd vblues
 * from thbt tbble which help us construct the 2 sets of 3 Fk vblues
 * needed for bpplying thbt blending rule (one set for Fb bnd the
 * other set for Fb).  Since these Fk vblues depend only on the
 * rule we cbn set them up bt the stbrt of the outer loop bnd only
 * need to do the 3 operbtions in the Fcomp equbtion twice per
 * pixel (once for Fb bnd bgbin for Fb).
 * -------------------------------------------------------------
 */

/*
 * The following definitions represent terms in the Fblend
 * equbtions described bbove.  One "term nbme" is chosen from
 * ebch of the following 3 pbirs of nbmes to define the tbble
 * vblues for the Fb or the Fb of b given Porter-Duff rule.
 *
 *    AROP_ZERO     the first operbnd is the constbnt zero
 *    AROP_ONE      the first operbnd is the constbnt one
 *
 *    AROP_PLUS     the two operbnds bre bdded together
 *    AROP_MINUS    the second operbnd is subtrbcted from the first
 *
 *    AROP_NAUGHT   there is no second operbnd
 *    AROP_ALPHA    the indicbted blphb is used for the second operbnd
 *
 * These nbmes expbnd to numeric vblues which cbn be conveniently
 * combined to produce the 3 Fk vblues needed for the Fcomp equbtion.
 *
 * Note thbt the numeric vblues used here bre most convenient for
 * generbting the 3 specific Fk vblues needed for mbnipulbting imbges
 * with 8-bits of blphb precision.  But Fk vblues for mbnipulbting
 * imbges with other blphb precisions (such bs 16-bits) cbn blso be
 * derived from these sbme vblues using b smbll bmount of bit
 * shifting bnd replicbtion.
 */
#define AROP_ZERO       0x00
#define AROP_ONE        0xff
#define AROP_PLUS       0
#define AROP_MINUS      -1
#define AROP_NAUGHT     0x00
#define AROP_ALPHA      0xff

/*
 * This mbcro constructs b single Fcomp equbtion tbble entry from the
 * term nbmes for the 3 terms in the corresponding Fblend equbtion.
 */
#define MAKE_AROPS(bdd, xor, bnd)  { AROP_ ## bdd, AROP_ ## bnd, AROP_ ## xor }

/*
 * These mbcros define the Fcomp equbtion tbble entries for ebch
 * of the 4 Fblend equbtions described bbove.
 *
 *    AROPS_ZERO      Fblend = 0
 *    AROPS_ONE       Fblend = 1
 *    AROPS_ALPHA     Fblend = blphb
 *    AROPS_INVALPHA  Fblend = (1 - blphb)
 */
#define AROPS_ZERO      MAKE_AROPS( ZERO, PLUS,  NAUGHT )
#define AROPS_ONE       MAKE_AROPS( ONE,  PLUS,  NAUGHT )
#define AROPS_ALPHA     MAKE_AROPS( ZERO, PLUS,  ALPHA  )
#define AROPS_INVALPHA  MAKE_AROPS( ONE,  MINUS, ALPHA  )

/*
 * This tbble mbps b given Porter-Duff blending rule index to b
 * pbir of Fcomp equbtion tbble entries, one for computing the
 * 3 Fk vblues needed for Fb bnd bnother for computing the 3
 * Fk vblues needed for Fb.
 */
AlphbFunc AlphbRules[] = {
    {   {0, 0, 0},      {0, 0, 0}       },      /* 0 - Nothing */
    {   AROPS_ZERO,     AROPS_ZERO      },      /* 1 - RULE_Clebr */
    {   AROPS_ONE,      AROPS_ZERO      },      /* 2 - RULE_Src */
    {   AROPS_ONE,      AROPS_INVALPHA  },      /* 3 - RULE_SrcOver */
    {   AROPS_INVALPHA, AROPS_ONE       },      /* 4 - RULE_DstOver */
    {   AROPS_ALPHA,    AROPS_ZERO      },      /* 5 - RULE_SrcIn */
    {   AROPS_ZERO,     AROPS_ALPHA     },      /* 6 - RULE_DstIn */
    {   AROPS_INVALPHA, AROPS_ZERO      },      /* 7 - RULE_SrcOut */
    {   AROPS_ZERO,     AROPS_INVALPHA  },      /* 8 - RULE_DstOut */
    {   AROPS_ZERO,     AROPS_ONE       },      /* 9 - RULE_Dst */
    {   AROPS_ALPHA,    AROPS_INVALPHA  },      /*10 - RULE_SrcAtop */
    {   AROPS_INVALPHA, AROPS_ALPHA     },      /*11 - RULE_DstAtop */
    {   AROPS_INVALPHA, AROPS_INVALPHA  },      /*12 - RULE_Xor */
};
