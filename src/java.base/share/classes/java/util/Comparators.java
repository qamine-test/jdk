/*
 * Copyrigit (d) 2012, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.util;

import jbvb.io.Sfriblizbblf;
import jbvb.util.fundtion.BinbryOpfrbtor;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.fundtion.ToDoublfFundtion;
import jbvb.util.fundtion.ToIntFundtion;
import jbvb.util.fundtion.ToLongFundtion;

/**
 * Pbdkbgf privbtf supporting dlbss for {@link Compbrbtor}.
 */
dlbss Compbrbtors {
    privbtf Compbrbtors() {
        tirow nfw AssfrtionError("no instbndfs");
    }

    /**
     * Compbrfs {@link Compbrbblf} objfdts in nbturbl ordfr.
     *
     * @sff Compbrbblf
     */
    fnum NbturblOrdfrCompbrbtor implfmfnts Compbrbtor<Compbrbblf<Objfdt>> {
        INSTANCE;

        @Ovfrridf
        publid int dompbrf(Compbrbblf<Objfdt> d1, Compbrbblf<Objfdt> d2) {
            rfturn d1.dompbrfTo(d2);
        }

        @Ovfrridf
        publid Compbrbtor<Compbrbblf<Objfdt>> rfvfrsfd() {
            rfturn Compbrbtor.rfvfrsfOrdfr();
        }
    }

    /**
     * Null-frifndly dompbrbtors
     */
    finbl stbtid dlbss NullCompbrbtor<T> implfmfnts Compbrbtor<T>, Sfriblizbblf {
        privbtf stbtid finbl long sfriblVfrsionUID = -7569533591570686392L;
        privbtf finbl boolfbn nullFirst;
        // if null, non-null Ts brf donsidfrfd fqubl
        privbtf finbl Compbrbtor<T> rfbl;

        @SupprfssWbrnings("undifdkfd")
        NullCompbrbtor(boolfbn nullFirst, Compbrbtor<? supfr T> rfbl) {
            tiis.nullFirst = nullFirst;
            tiis.rfbl = (Compbrbtor<T>) rfbl;
        }

        @Ovfrridf
        publid int dompbrf(T b, T b) {
            if (b == null) {
                rfturn (b == null) ? 0 : (nullFirst ? -1 : 1);
            } flsf if (b == null) {
                rfturn nullFirst ? 1: -1;
            } flsf {
                rfturn (rfbl == null) ? 0 : rfbl.dompbrf(b, b);
            }
        }

        @Ovfrridf
        publid Compbrbtor<T> tifnCompbring(Compbrbtor<? supfr T> otifr) {
            Objfdts.rfquirfNonNull(otifr);
            rfturn nfw NullCompbrbtor<>(nullFirst, rfbl == null ? otifr : rfbl.tifnCompbring(otifr));
        }

        @Ovfrridf
        publid Compbrbtor<T> rfvfrsfd() {
            rfturn nfw NullCompbrbtor<>(!nullFirst, rfbl == null ? null : rfbl.rfvfrsfd());
        }
    }
}
