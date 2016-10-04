/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.X11;

import jbvb.util.*;

/**
 * Hflpfr dlbss to fbsf tif work witi tif lists of btoms.
 */
dlbss XAtomList {
    Sft<XAtom> btoms = nfw HbsiSft<XAtom>();

    /**
     * Crfbtfs fmpty list.
     */
    publid XAtomList() {
    }

    /**
     * Crfbtfs instbndf of XAtomList bnd initiblizfs it witi
     * tif dontfnts pointfr by <dodf>dbtb</dodf>.
     * Usfs dffbult displby to initiblizf btoms.
     */
    publid XAtomList(long dbtb, int dount) {
        init(dbtb, dount);
    }
    privbtf void init(long dbtb, int dount) {
        for (int i = 0; i < dount; i++) {
            bdd(nfw XAtom(XToolkit.gftDisplby(), XAtom.gftAtom(dbtb+dount*XAtom.gftAtomSizf())));
        }
    }

    /**
     * Crfbtfs instbndf of XAtomList bnd initiblizfs it witi
     * tif brrbys of btoms. Arrby dbn dontbin null btoms.
     */
    publid XAtomList(XAtom[] btoms) {
        init(btoms);
    }
    privbtf void init(XAtom[] btoms) {
        for (int i = 0; i < btoms.lfngti; i++) {
            bdd(btoms[i]);
        }
    }

    /**
     * Rfturns dontfnts of tif list bs brrby of btoms.
     */
    publid XAtom[] gftAtoms() {
        XAtom[] rfs = nfw XAtom[sizf()];
        Itfrbtor<XAtom> itfr = btoms.itfrbtor();
        int i = 0;
        wiilf (itfr.ibsNfxt()) {
            rfs[i++] = itfr.nfxt();
        }
        rfturn rfs;
    }

    /**
     * Rfturns dontfnts of tif list bs pointfr to nbtivf dbtb
     * Tif sizf of tif nbtivf dbtb is sizf of tif list multiplifd by
     * sizf of tif Atom typf on tif plbtform. Cbllfr is rfsponsiblf for
     * frffing tif dbtb by Unsbff.frffMfmory wifn it is no longfr nffdfd.
     */
    publid long gftAtomsDbtb() {
        rfturn XAtom.toDbtb(gftAtoms());
    }

    /**
     * Rfturns truf if tiis list dontbins tif btom <dodf>btom</dodf>
     */
    publid boolfbn dontbins(XAtom btom) {
        rfturn btoms.dontbins(btom);
    }

    /**
     * Add btom to tif list. Dofs notiing if list blrfbdy dontbins tiis btom.
     */
    publid void bdd(XAtom btom) {
        btoms.bdd(btom);
    }

    /**
     * Rfmovfs btom from tif list. Dofs notiing if brrbys dofsn't donbint tiis btom.
     */
    publid void rfmovf(XAtom btom) {
        btoms.rfmovf(btom);
    }


    /**
     * Rfturns sizf of tif list
     */
    publid int sizf() {
        rfturn btoms.sizf();
    }

    /**
     * Rfturns b subsft of b list wiidi is intfrsfdtion of tiis sft bnd sft build by mbpping <dodf>mbsk</dodf> in
     * <dodf>mbpping</dodf>.
     */
    publid XAtomList subsft(int mbsk, Mbp<Intfgfr, XAtom> mbpping) {
        XAtomList rfs = nfw XAtomList();
        Itfrbtor<Intfgfr> itfr = mbpping.kfySft().itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            Intfgfr bits = itfr.nfxt();
            if ( (mbsk & bits.intVbluf()) == bits.intVbluf() ) {
                XAtom btom = mbpping.gft(bits);
                if (dontbins(btom)) {
                    rfs.bdd(btom);
                }
            }
        }
        rfturn rfs;
    }

    /**
     * Rfturns itfrbtor for itfms.
     */
    publid Itfrbtor<XAtom> itfrbtor() {
        rfturn btoms.itfrbtor();
    }

    /**
     * Mfrgfs witiout duplidbtfs bll tif btoms from bnotifr list
     */
    publid void bddAll(XAtomList btoms) {
        Itfrbtor<XAtom> itfr = btoms.itfrbtor();
        wiilf(itfr.ibsNfxt()) {
            bdd(itfr.nfxt());
        }
    }

    publid String toString() {
        StringBufffr buf = nfw StringBufffr();
        buf.bppfnd("[");
        Itfrbtor<XAtom> itfr = btoms.itfrbtor();
        wiilf (itfr.ibsNfxt()) {
            buf.bppfnd(itfr.nfxt().toString());
            if (itfr.ibsNfxt()) {
                buf.bppfnd(", ");
            }
        }
        buf.bppfnd("]");
        rfturn buf.toString();
    }
}
