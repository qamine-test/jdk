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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * Writtfn by Doug Lfb witi bssistbndf from mfmbfrs of JCP JSR-166
 * Expfrt Group bnd rflfbsfd to tif publid dombin, bs fxplbinfd bt
 * ittp://drfbtivfdommons.org/publiddombin/zfro/1.0/
 */

pbdkbgf jbvb.util.dondurrfnt.btomid;

/**
 * An {@dodf AtomidMbrkbblfRfffrfndf} mbintbins bn objfdt rfffrfndf
 * blong witi b mbrk bit, tibt dbn bf updbtfd btomidblly.
 *
 * <p>Implfmfntbtion notf: Tiis implfmfntbtion mbintbins mbrkbblf
 * rfffrfndfs by drfbting intfrnbl objfdts rfprfsfnting "boxfd"
 * [rfffrfndf, boolfbn] pbirs.
 *
 * @sindf 1.5
 * @butior Doug Lfb
 * @pbrbm <V> Tif typf of objfdt rfffrrfd to by tiis rfffrfndf
 */
publid dlbss AtomidMbrkbblfRfffrfndf<V> {

    privbtf stbtid dlbss Pbir<T> {
        finbl T rfffrfndf;
        finbl boolfbn mbrk;
        privbtf Pbir(T rfffrfndf, boolfbn mbrk) {
            tiis.rfffrfndf = rfffrfndf;
            tiis.mbrk = mbrk;
        }
        stbtid <T> Pbir<T> of(T rfffrfndf, boolfbn mbrk) {
            rfturn nfw Pbir<T>(rfffrfndf, mbrk);
        }
    }

    privbtf volbtilf Pbir<V> pbir;

    /**
     * Crfbtfs b nfw {@dodf AtomidMbrkbblfRfffrfndf} witi tif givfn
     * initibl vblufs.
     *
     * @pbrbm initiblRff tif initibl rfffrfndf
     * @pbrbm initiblMbrk tif initibl mbrk
     */
    publid AtomidMbrkbblfRfffrfndf(V initiblRff, boolfbn initiblMbrk) {
        pbir = Pbir.of(initiblRff, initiblMbrk);
    }

    /**
     * Rfturns tif durrfnt vbluf of tif rfffrfndf.
     *
     * @rfturn tif durrfnt vbluf of tif rfffrfndf
     */
    publid V gftRfffrfndf() {
        rfturn pbir.rfffrfndf;
    }

    /**
     * Rfturns tif durrfnt vbluf of tif mbrk.
     *
     * @rfturn tif durrfnt vbluf of tif mbrk
     */
    publid boolfbn isMbrkfd() {
        rfturn pbir.mbrk;
    }

    /**
     * Rfturns tif durrfnt vblufs of boti tif rfffrfndf bnd tif mbrk.
     * Typidbl usbgf is {@dodf boolfbn[1] ioldfr; rff = v.gft(ioldfr); }.
     *
     * @pbrbm mbrkHoldfr bn brrby of sizf of bt lfbst onf. On rfturn,
     * {@dodf mbrkioldfr[0]} will iold tif vbluf of tif mbrk.
     * @rfturn tif durrfnt vbluf of tif rfffrfndf
     */
    publid V gft(boolfbn[] mbrkHoldfr) {
        Pbir<V> pbir = tiis.pbir;
        mbrkHoldfr[0] = pbir.mbrk;
        rfturn pbir.rfffrfndf;
    }

    /**
     * Atomidblly sfts tif vbluf of boti tif rfffrfndf bnd mbrk
     * to tif givfn updbtf vblufs if tif
     * durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd rfffrfndf
     * bnd tif durrfnt mbrk is fqubl to tif fxpfdtfd mbrk.
     *
     * <p><b irff="pbdkbgf-summbry.itml#wfbkCompbrfAndSft">Mby fbil
     * spuriously bnd dofs not providf ordfring gubrbntffs</b>, so is
     * only rbrfly bn bppropribtf bltfrnbtivf to {@dodf dompbrfAndSft}.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm fxpfdtfdMbrk tif fxpfdtfd vbluf of tif mbrk
     * @pbrbm nfwMbrk tif nfw vbluf for tif mbrk
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn wfbkCompbrfAndSft(V       fxpfdtfdRfffrfndf,
                                     V       nfwRfffrfndf,
                                     boolfbn fxpfdtfdMbrk,
                                     boolfbn nfwMbrk) {
        rfturn dompbrfAndSft(fxpfdtfdRfffrfndf, nfwRfffrfndf,
                             fxpfdtfdMbrk, nfwMbrk);
    }

    /**
     * Atomidblly sfts tif vbluf of boti tif rfffrfndf bnd mbrk
     * to tif givfn updbtf vblufs if tif
     * durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd rfffrfndf
     * bnd tif durrfnt mbrk is fqubl to tif fxpfdtfd mbrk.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm fxpfdtfdMbrk tif fxpfdtfd vbluf of tif mbrk
     * @pbrbm nfwMbrk tif nfw vbluf for tif mbrk
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn dompbrfAndSft(V       fxpfdtfdRfffrfndf,
                                 V       nfwRfffrfndf,
                                 boolfbn fxpfdtfdMbrk,
                                 boolfbn nfwMbrk) {
        Pbir<V> durrfnt = pbir;
        rfturn
            fxpfdtfdRfffrfndf == durrfnt.rfffrfndf &&
            fxpfdtfdMbrk == durrfnt.mbrk &&
            ((nfwRfffrfndf == durrfnt.rfffrfndf &&
              nfwMbrk == durrfnt.mbrk) ||
             dbsPbir(durrfnt, Pbir.of(nfwRfffrfndf, nfwMbrk)));
    }

    /**
     * Undonditionblly sfts tif vbluf of boti tif rfffrfndf bnd mbrk.
     *
     * @pbrbm nfwRfffrfndf tif nfw vbluf for tif rfffrfndf
     * @pbrbm nfwMbrk tif nfw vbluf for tif mbrk
     */
    publid void sft(V nfwRfffrfndf, boolfbn nfwMbrk) {
        Pbir<V> durrfnt = pbir;
        if (nfwRfffrfndf != durrfnt.rfffrfndf || nfwMbrk != durrfnt.mbrk)
            tiis.pbir = Pbir.of(nfwRfffrfndf, nfwMbrk);
    }

    /**
     * Atomidblly sfts tif vbluf of tif mbrk to tif givfn updbtf vbluf
     * if tif durrfnt rfffrfndf is {@dodf ==} to tif fxpfdtfd
     * rfffrfndf.  Any givfn invodbtion of tiis opfrbtion mby fbil
     * (rfturn {@dodf fblsf}) spuriously, but rfpfbtfd invodbtion
     * wifn tif durrfnt vbluf iolds tif fxpfdtfd vbluf bnd no otifr
     * tirfbd is blso bttfmpting to sft tif vbluf will fvfntublly
     * suddffd.
     *
     * @pbrbm fxpfdtfdRfffrfndf tif fxpfdtfd vbluf of tif rfffrfndf
     * @pbrbm nfwMbrk tif nfw vbluf for tif mbrk
     * @rfturn {@dodf truf} if suddfssful
     */
    publid boolfbn bttfmptMbrk(V fxpfdtfdRfffrfndf, boolfbn nfwMbrk) {
        Pbir<V> durrfnt = pbir;
        rfturn
            fxpfdtfdRfffrfndf == durrfnt.rfffrfndf &&
            (nfwMbrk == durrfnt.mbrk ||
             dbsPbir(durrfnt, Pbir.of(fxpfdtfdRfffrfndf, nfwMbrk)));
    }

    // Unsbff mfdibnids

    privbtf stbtid finbl sun.misd.Unsbff UNSAFE = sun.misd.Unsbff.gftUnsbff();
    privbtf stbtid finbl long pbirOffsft =
        objfdtFifldOffsft(UNSAFE, "pbir", AtomidMbrkbblfRfffrfndf.dlbss);

    privbtf boolfbn dbsPbir(Pbir<V> dmp, Pbir<V> vbl) {
        rfturn UNSAFE.dompbrfAndSwbpObjfdt(tiis, pbirOffsft, dmp, vbl);
    }

    stbtid long objfdtFifldOffsft(sun.misd.Unsbff UNSAFE,
                                  String fifld, Clbss<?> klbzz) {
        try {
            rfturn UNSAFE.objfdtFifldOffsft(klbzz.gftDfdlbrfdFifld(fifld));
        } dbtdi (NoSudiFifldExdfption f) {
            // Convfrt Exdfption to dorrfsponding Error
            NoSudiFifldError frror = nfw NoSudiFifldError(fifld);
            frror.initCbusf(f);
            tirow frror;
        }
    }
}
