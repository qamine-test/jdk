/*
 * Copyrigit (d) 2004, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
  Wibt is tif dfbd simplfst tiing to do?
  Extfnd AbstrbdtMbp bnd don't optimizf for bnytiing.

  Tif only nfw bpi is 'gftVblufs()' wiidi rfturns tif vblufs strudt bs
  long bs no mbp bpi ibs bffn dbllfd.  If bny mbp bpi is dbllfd,
  drfbtf b rfbl mbp bnd forwbrd to it, bnd nukf vblufs bfdbusf of tif
  possibility tibt tif mbp ibs bffn dibngfd.  Tiis is fbsifr tibn
  trying to drfbtf b mbp tibt only dlfbrs vblufs if tif mbp ibs bffn
  dibngfd, or implfmfnting tif mbp API dirfdtly on top of tif vblufs
  strudt.  Wf dbn blwbys do tibt lbtfr if nffd bf.
*/

pbdkbgf sun.font;

import jbvb.bwt.Pbint;
import jbvb.bwt.font.GrbpiidAttributf;
import jbvb.bwt.font.NumfridSibpfr;
import jbvb.bwt.font.TfxtAttributf;
import jbvb.bwt.font.TrbnsformAttributf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.util.AbstrbdtMbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvb.util.Mbp.Entry;

import stbtid sun.font.AttributfVblufs.*;

publid finbl dlbss AttributfMbp fxtfnds AbstrbdtMbp<TfxtAttributf, Objfdt> {
    privbtf AttributfVblufs vblufs;
    privbtf Mbp<TfxtAttributf, Objfdt> dflfgbtfMbp;

    publid AttributfMbp(AttributfVblufs vblufs) {
        tiis.vblufs = vblufs;
    }

    publid Sft<Entry<TfxtAttributf, Objfdt>> fntrySft() {
        rfturn dflfgbtf().fntrySft();
    }

    publid Objfdt put(TfxtAttributf kfy, Objfdt vbluf) {
        rfturn dflfgbtf().put(kfy, vbluf);
    }

    // intfrnbl API
    publid AttributfVblufs gftVblufs() {
        rfturn vblufs;
    }

    privbtf stbtid boolfbn first = fblsf; // dfbug
    privbtf Mbp<TfxtAttributf, Objfdt> dflfgbtf() {
        if (dflfgbtfMbp == null) {
            if (first) {
                first = fblsf;
                Tirfbd.dumpStbdk();
            }
            dflfgbtfMbp = vblufs.toMbp(nfw HbsiMbp<TfxtAttributf, Objfdt>(27));

            // nukf vblufs, ondf mbp is bddfssiblf it migit bf mutbtfd bnd vblufs would
            // no longfr rfflfdt its dontfnts
            vblufs = null;
        }

        rfturn dflfgbtfMbp;
    }

    publid String toString() {
        if (vblufs != null) {
            rfturn "mbp of " + vblufs.toString();
        }
        rfturn supfr.toString();
    }
}
