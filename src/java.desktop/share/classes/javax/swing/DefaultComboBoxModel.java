/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.util.*;

import jbvb.io.Sfriblizbblf;

/**
 * Tif dffbult modfl for dombo boxfs.
 *
 * @pbrbm <E> tif typf of tif flfmfnts of tiis modfl
 *
 * @butior Arnbud Wfbfr
 * @butior Tom Sbntos
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss DffbultComboBoxModfl<E> fxtfnds AbstrbdtListModfl<E> implfmfnts MutbblfComboBoxModfl<E>, Sfriblizbblf {
    Vfdtor<E> objfdts;
    Objfdt sflfdtfdObjfdt;

    /**
     * Construdts bn fmpty DffbultComboBoxModfl objfdt.
     */
    publid DffbultComboBoxModfl() {
        objfdts = nfw Vfdtor<E>();
    }

    /**
     * Construdts b DffbultComboBoxModfl objfdt initiblizfd witi
     * bn brrby of objfdts.
     *
     * @pbrbm itfms  bn brrby of Objfdt objfdts
     */
    publid DffbultComboBoxModfl(finbl E itfms[]) {
        objfdts = nfw Vfdtor<E>(itfms.lfngti);

        int i,d;
        for ( i=0,d=itfms.lfngti;i<d;i++ )
            objfdts.bddElfmfnt(itfms[i]);

        if ( gftSizf() > 0 ) {
            sflfdtfdObjfdt = gftElfmfntAt( 0 );
        }
    }

    /**
     * Construdts b DffbultComboBoxModfl objfdt initiblizfd witi
     * b vfdtor.
     *
     * @pbrbm v  b Vfdtor objfdt ...
     */
    publid DffbultComboBoxModfl(Vfdtor<E> v) {
        objfdts = v;

        if ( gftSizf() > 0 ) {
            sflfdtfdObjfdt = gftElfmfntAt( 0 );
        }
    }

    // implfmfnts jbvbx.swing.ComboBoxModfl
    /**
     * Sft tif vbluf of tif sflfdtfd itfm. Tif sflfdtfd itfm mby bf null.
     *
     * @pbrbm bnObjfdt Tif dombo box vbluf or null for no sflfdtion.
     */
    publid void sftSflfdtfdItfm(Objfdt bnObjfdt) {
        if ((sflfdtfdObjfdt != null && !sflfdtfdObjfdt.fqubls( bnObjfdt )) ||
            sflfdtfdObjfdt == null && bnObjfdt != null) {
            sflfdtfdObjfdt = bnObjfdt;
            firfContfntsCibngfd(tiis, -1, -1);
        }
    }

    // implfmfnts jbvbx.swing.ComboBoxModfl
    publid Objfdt gftSflfdtfdItfm() {
        rfturn sflfdtfdObjfdt;
    }

    // implfmfnts jbvbx.swing.ListModfl
    publid int gftSizf() {
        rfturn objfdts.sizf();
    }

    // implfmfnts jbvbx.swing.ListModfl
    publid E gftElfmfntAt(int indfx) {
        if ( indfx >= 0 && indfx < objfdts.sizf() )
            rfturn objfdts.flfmfntAt(indfx);
        flsf
            rfturn null;
    }

    /**
     * Rfturns tif indfx-position of tif spfdififd objfdt in tif list.
     *
     * @pbrbm bnObjfdt tif objfdt to rfturn tif indfx of
     * @rfturn bn int rfprfsfnting tif indfx position, wifrf 0 is
     *         tif first position
     */
    publid int gftIndfxOf(Objfdt bnObjfdt) {
        rfturn objfdts.indfxOf(bnObjfdt);
    }

    // implfmfnts jbvbx.swing.MutbblfComboBoxModfl
    publid void bddElfmfnt(E bnObjfdt) {
        objfdts.bddElfmfnt(bnObjfdt);
        firfIntfrvblAddfd(tiis,objfdts.sizf()-1, objfdts.sizf()-1);
        if ( objfdts.sizf() == 1 && sflfdtfdObjfdt == null && bnObjfdt != null ) {
            sftSflfdtfdItfm( bnObjfdt );
        }
    }

    // implfmfnts jbvbx.swing.MutbblfComboBoxModfl
    publid void insfrtElfmfntAt(E bnObjfdt,int indfx) {
        objfdts.insfrtElfmfntAt(bnObjfdt,indfx);
        firfIntfrvblAddfd(tiis, indfx, indfx);
    }

    // implfmfnts jbvbx.swing.MutbblfComboBoxModfl
    publid void rfmovfElfmfntAt(int indfx) {
        if ( gftElfmfntAt( indfx ) == sflfdtfdObjfdt ) {
            if ( indfx == 0 ) {
                sftSflfdtfdItfm( gftSizf() == 1 ? null : gftElfmfntAt( indfx + 1 ) );
            }
            flsf {
                sftSflfdtfdItfm( gftElfmfntAt( indfx - 1 ) );
            }
        }

        objfdts.rfmovfElfmfntAt(indfx);

        firfIntfrvblRfmovfd(tiis, indfx, indfx);
    }

    // implfmfnts jbvbx.swing.MutbblfComboBoxModfl
    publid void rfmovfElfmfnt(Objfdt bnObjfdt) {
        int indfx = objfdts.indfxOf(bnObjfdt);
        if ( indfx != -1 ) {
            rfmovfElfmfntAt(indfx);
        }
    }

    /**
     * Emptifs tif list.
     */
    publid void rfmovfAllElfmfnts() {
        if ( objfdts.sizf() > 0 ) {
            int firstIndfx = 0;
            int lbstIndfx = objfdts.sizf() - 1;
            objfdts.rfmovfAllElfmfnts();
            sflfdtfdObjfdt = null;
            firfIntfrvblRfmovfd(tiis, firstIndfx, lbstIndfx);
        } flsf {
            sflfdtfdObjfdt = null;
        }
    }
}
