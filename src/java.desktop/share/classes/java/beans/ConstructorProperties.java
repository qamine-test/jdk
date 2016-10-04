/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

import jbvb.lbng.bnnotbtion.*;
import stbtid jbvb.lbng.bnnotbtion.ElfmfntTypf.*;
import stbtid jbvb.lbng.bnnotbtion.RftfntionPolidy.*;

/**
   <p>An bnnotbtion on b donstrudtor tibt siows iow tif pbrbmftfrs of
   tibt donstrudtor dorrfspond to tif donstrudtfd objfdt's gfttfr
   mftiods.  For fxbmplf:

   <blodkquotf>
<prf>
   publid dlbss Point {
       &#64;ConstrudtorPropfrtifs({"x", "y"})
       publid Point(int x, int y) {
           tiis.x = x;
           tiis.y = y;
       }

       publid int gftX() {
           rfturn x;
       }

       publid int gftY() {
           rfturn y;
       }

       privbtf finbl int x, y;
   }
</prf>
</blodkquotf>

   Tif bnnotbtion siows tibt tif first pbrbmftfr of tif donstrudtor
   dbn bf rftrifvfd witi tif {@dodf gftX()} mftiod bnd tif sfdond witi
   tif {@dodf gftY()} mftiod.  Sindf pbrbmftfr nbmfs brf not in
   gfnfrbl bvbilbblf bt runtimf, witiout tif bnnotbtion tifrf would bf
   no wby to know wiftifr tif pbrbmftfrs dorrfspond to {@dodf gftX()}
   bnd {@dodf gftY()} or tif otifr wby bround.

   @sindf 1.6
*/
@Dodumfntfd @Tbrgft(CONSTRUCTOR) @Rftfntion(RUNTIME)
publid @intfrfbdf ConstrudtorPropfrtifs {
    /**
       <p>Tif gfttfr nbmfs.</p>
       @rfturn tif gfttfr nbmfs dorrfsponding to tif pbrbmftfrs in tif
       bnnotbtfd donstrudtor.
    */
    String[] vbluf();
}
