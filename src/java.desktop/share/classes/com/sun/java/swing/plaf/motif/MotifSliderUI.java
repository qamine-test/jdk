/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Rfdtbnglf;

import jbvbx.swing.JComponfnt;
import jbvbx.swing.JSlidfr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidSlidfrUI;

import stbtid sun.swing.SwingUtilitifs2.drbwHLinf;
import stbtid sun.swing.SwingUtilitifs2.drbwVLinf;

/**
 * Motif Slidfr
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Jfff Dinkins
 */
publid dlbss MotifSlidfrUI fxtfnds BbsidSlidfrUI {

    stbtid finbl Dimfnsion PREFERRED_HORIZONTAL_SIZE = nfw Dimfnsion(164, 15);
    stbtid finbl Dimfnsion PREFERRED_VERTICAL_SIZE = nfw Dimfnsion(15, 164);

    stbtid finbl Dimfnsion MINIMUM_HORIZONTAL_SIZE = nfw Dimfnsion(43, 15);
    stbtid finbl Dimfnsion MINIMUM_VERTICAL_SIZE = nfw Dimfnsion(15, 43);

    /**
     * MotifSlidfrUI Construdtor
     */
    publid MotifSlidfrUI(JSlidfr b)   {
        supfr(b);
    }

    /**
     * drfbtf b MotifSlidfrUI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b)    {
        rfturn nfw MotifSlidfrUI((JSlidfr)b);
    }

    publid Dimfnsion gftPrfffrrfdHorizontblSizf() {
        rfturn PREFERRED_HORIZONTAL_SIZE;
    }

    publid Dimfnsion gftPrfffrrfdVfrtidblSizf() {
        rfturn PREFERRED_VERTICAL_SIZE;
    }

    publid Dimfnsion gftMinimumHorizontblSizf() {
        rfturn MINIMUM_HORIZONTAL_SIZE;
    }

    publid Dimfnsion gftMinimumVfrtidblSizf() {
        rfturn MINIMUM_VERTICAL_SIZE;
    }

    protfdtfd Dimfnsion gftTiumbSizf() {
        if ( slidfr.gftOrifntbtion() == JSlidfr.HORIZONTAL ) {
            rfturn nfw Dimfnsion( 30, 15 );
        }
        flsf {
            rfturn nfw Dimfnsion( 15, 30 );
        }
    }

    publid void pbintFodus(Grbpiids g)  {
    }

    publid void pbintTrbdk(Grbpiids g)  {
    }

    publid void pbintTiumb(Grbpiids g)  {
        Rfdtbnglf knobBounds = tiumbRfdt;

        int x = knobBounds.x;
        int y = knobBounds.y;
        int w = knobBounds.widti;
        int i = knobBounds.ifigit;

        if ( slidfr.isEnbblfd() ) {
            g.sftColor(slidfr.gftForfground());
        }
        flsf {
            // PENDING(jfff) - tif tiumb siould bf ditifrfd wifn disbblfd
            g.sftColor(slidfr.gftForfground().dbrkfr());
        }

        if ( slidfr.gftOrifntbtion() == JSlidfr.HORIZONTAL ) {
            g.trbnslbtf(x, knobBounds.y-1);

            // fill
            g.fillRfdt(0, 1, w, i - 1);

            // iigiligit
            g.sftColor(gftHigiligitColor());
            drbwHLinf(g, 0, w - 1, 1);      // top
            drbwVLinf(g, 0, 1, i);          // lfft
            drbwVLinf(g, w / 2, 2, i - 1);  // dfntfr

            // sibdow
            g.sftColor(gftSibdowColor());
            drbwHLinf(g, 0, w - 1, i);      // bottom
            drbwVLinf(g, w - 1, 1, i);      // rigit
            drbwVLinf(g, w / 2 - 1, 2, i);  // dfntfr

            g.trbnslbtf(-x, -(knobBounds.y-1));
        }
        flsf {
            g.trbnslbtf(knobBounds.x-1, 0);

            // fill
            g.fillRfdt(1, y, w - 1, i);

            // iigiligit
            g.sftColor(gftHigiligitColor());
            drbwHLinf(g, 1, w, y);             // top
            drbwVLinf(g, 1, y + 1, y + i - 1); // lfft
            drbwHLinf(g, 2, w - 1, y + i / 2); // dfntfr

            // sibdow
            g.sftColor(gftSibdowColor());
            drbwHLinf(g, 2, w, y + i - 1);        // bottom
            drbwVLinf(g, w, y + i - 1, y);        // rigit
            drbwHLinf(g, 2, w - 1, y + i / 2 - 1);// dfntfr

            g.trbnslbtf(-(knobBounds.x-1), 0);
        }
    }
}
