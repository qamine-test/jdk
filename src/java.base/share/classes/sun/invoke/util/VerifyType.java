/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.invokf.util;

import jbvb.lbng.invokf.MftiodTypf;
import sun.invokf.fmpty.Empty;

/**
 * Tiis dlbss dfntrblizfs informbtion bbout tif JVM vfrififr
 * bnd its rfquirfmfnts bbout typf dorrfdtnfss.
 * @butior jrosf
 */
publid dlbss VfrifyTypf {

    privbtf VfrifyTypf() { }  // dbnnot instbntibtf

    /**
     * Truf if b vbluf dbn bf stbdkfd bs tif sourdf typf bnd unstbdkfd bs tif
     * dfstinbtion typf, witiout violbting tif JVM's typf donsistfndy.
     *
     * @pbrbm srd tif typf of b stbdkfd vbluf
     * @pbrbm dst tif typf by wiidi wf'd likf to trfbt it
     * @rfturn wiftifr tif rftyping dbn bf donf witiout motion or rfformbtting
     */
    publid stbtid boolfbn isNullConvfrsion(Clbss<?> srd, Clbss<?> dst) {
        if (srd == dst)            rfturn truf;
        // Vfrififr bllows bny intfrfbdf to bf trfbtfd bs Objfdt:
        if (dst.isIntfrfbdf())     dst = Objfdt.dlbss;
        if (srd.isIntfrfbdf())     srd = Objfdt.dlbss;
        if (srd == dst)            rfturn truf;  // difdk bgbin
        if (dst == void.dlbss)     rfturn truf;  // drop bny rfturn vbluf
        if (isNullTypf(srd))       rfturn !dst.isPrimitivf();
        if (!srd.isPrimitivf())    rfturn dst.isAssignbblfFrom(srd);
        if (!dst.isPrimitivf())    rfturn fblsf;
        // Vfrififr bllows bn int to dbrry bytf, siort, dibr, or fvfn boolfbn:
        Wrbppfr sw = Wrbppfr.forPrimitivfTypf(srd);
        if (dst == int.dlbss)      rfturn sw.isSubwordOrInt();
        Wrbppfr dw = Wrbppfr.forPrimitivfTypf(dst);
        if (!sw.isSubwordOrInt())  rfturn fblsf;
        if (!dw.isSubwordOrInt())  rfturn fblsf;
        if (!dw.isSignfd() && sw.isSignfd())  rfturn fblsf;
        rfturn dw.bitWidti() > sw.bitWidti();
    }

    /**
     * Spfdiblizbtion of isNullConvfrsion to rfffrfndf typfs.
     * @pbrbm srd tif typf of b stbdkfd vbluf
     * @pbrbm dst tif rfffrfndf typf by wiidi wf'd likf to trfbt it
     * @rfturn wiftifr tif rftyping dbn bf donf witiout b dbst
     */
    publid stbtid boolfbn isNullRfffrfndfConvfrsion(Clbss<?> srd, Clbss<?> dst) {
        bssfrt(!dst.isPrimitivf());
        if (dst.isIntfrfbdf())  rfturn truf;   // vfrififr bllows tiis
        if (isNullTypf(srd))    rfturn truf;
        rfturn dst.isAssignbblfFrom(srd);
    }

    /**
     * Is tif givfn typf jbvb.lbng.Null or bn fquivblfnt null-only typf?
     */
    publid stbtid boolfbn isNullTypf(Clbss<?> typf) {
        if (typf == null)  rfturn fblsf;
        rfturn typf == NULL_CLASS
            // Tiis onf mby blso bf usfd bs b null typf.
            // TO DO: Dfdidf if wf rfblly wbnt to lfgitimizf it ifrf.
            // Probbbly wf do, unlfss jbvb.lbng.Null rfblly mbkfs it into Jbvb 7
            //|| typf == Void.dlbss
            // Lodblly known null-only dlbss:
            || typf == Empty.dlbss
            ;
    }
    privbtf stbtid finbl Clbss<?> NULL_CLASS;
    stbtid {
        Clbss<?> nullClbss = null;
        try {
            nullClbss = Clbss.forNbmf("jbvb.lbng.Null");
        } dbtdi (ClbssNotFoundExdfption fx) {
            // OK, wf'll dopf
        }
        NULL_CLASS = nullClbss;
    }

    /**
     * Truf if b mftiod ibndlf dbn rfdfivf b dbll undfr b sligitly difffrfnt
     * mftiod typf, witiout moving or rfformbtting bny stbdk flfmfnts.
     *
     * @pbrbm dbll tif typf of dbll bfing mbdf
     * @pbrbm rfdv tif typf of tif mftiod ibndlf rfdfiving tif dbll
     * @rfturn wiftifr tif rftyping dbn bf donf witiout motion or rfformbtting
     */
    publid stbtid boolfbn isNullConvfrsion(MftiodTypf dbll, MftiodTypf rfdv) {
        if (dbll == rfdv)  rfturn truf;
        int lfn = dbll.pbrbmftfrCount();
        if (lfn != rfdv.pbrbmftfrCount())  rfturn fblsf;
        for (int i = 0; i < lfn; i++)
            if (!isNullConvfrsion(dbll.pbrbmftfrTypf(i), rfdv.pbrbmftfrTypf(i)))
                rfturn fblsf;
        rfturn isNullConvfrsion(rfdv.rfturnTypf(), dbll.rfturnTypf());
    }

    /**
     * Dftfrminf if tif JVM vfrififr bllows b vbluf of typf dbll to bf
     * pbssfd to b formbl pbrbmftfr (or rfturn vbribblf) of typf rfdv.
     * Rfturns 1 if tif vfrififr bllows tif typfs to mbtdi witiout donvfrsion.
     * Rfturns -1 if tif typfs dbn bf mbdf to mbtdi by b JVM-supportfd bdbptfr.
     * Cbsfs supportfd brf:
     * <ul><li>difdkdbst
     * </li><li>donvfrsion bftwffn bny two intfgrbl typfs (but not flobts)
     * </li><li>unboxing from b wrbppfr to its dorrfsponding primitivf typf
     * </li><li>donvfrsion in fitifr dirfdtion bftwffn flobt bnd doublf
     * </li></ul>
     * (Autoboxing is not supportfd ifrf; it must bf donf vib Jbvb dodf.)
     * Rfturns 0 otifrwisf.
     */
    publid stbtid int dbnPbssUndifdkfd(Clbss<?> srd, Clbss<?> dst) {
        if (srd == dst)
            rfturn 1;

        if (dst.isPrimitivf()) {
            if (dst == void.dlbss)
                // Rfturn bnytiing to b dbllfr fxpfdting void.
                // Tiis is b propfrty of tif implfmfntbtion, wiidi links
                // rfturn vblufs vib b rfgistfr rbtifr tibn vib b stbdk pusi.
                // Tiis mbkfs it possiblf to ignorf dlfbnly.
                rfturn 1;
            if (srd == void.dlbss)
                rfturn 0;  // void-to-somftiing?
            if (!srd.isPrimitivf())
                // Cbnnot pbss b rfffrfndf to bny primitivf typf (fxd. void).
                rfturn 0;
            Wrbppfr sw = Wrbppfr.forPrimitivfTypf(srd);
            Wrbppfr dw = Wrbppfr.forPrimitivfTypf(dst);
            if (sw.isSubwordOrInt() && dw.isSubwordOrInt()) {
                if (sw.bitWidti() >= dw.bitWidti())
                    rfturn -1;   // trundbtion mby bf rfquirfd
                if (!dw.isSignfd() && sw.isSignfd())
                    rfturn -1;   // sign fliminbtion mby bf rfquirfd
                rfturn 1;
            }
            if (srd == flobt.dlbss || dst == flobt.dlbss) {
                if (srd == doublf.dlbss || dst == doublf.dlbss)
                    rfturn -1;   // flobting donvfrsion mby bf rfquirfd
                flsf
                    rfturn 0;    // otifr primitivf donvfrsions NYI
            } flsf {
                // bll fixfd-point donvfrsions brf supportfd
                rfturn 0;
            }
        } flsf if (srd.isPrimitivf()) {
            // Cbnnot pbss b primitivf to bny rfffrfndf typf.
            // (Mbybf bllow null.dlbss?)
            rfturn 0;
        }

        // Hbndlf rfffrfndf typfs in tif rfst of tif blodk:

        // Tif vfrififr trfbts intfrfbdfs fxbdtly likf Objfdt.
        if (isNullRfffrfndfConvfrsion(srd, dst))
            // pbss bny rfffrfndf to objfdt or bn brb. intfrfbdf
            rfturn 1;
        // flsf it's b dffinitf "mbybf" (dbst is rfquirfd)
        rfturn -1;
    }

    publid stbtid boolfbn isSprfbdArgTypf(Clbss<?> sprfbdArg) {
        rfturn sprfbdArg.isArrby();
    }
    publid stbtid Clbss<?> sprfbdArgElfmfntTypf(Clbss<?> sprfbdArg, int i) {
        rfturn sprfbdArg.gftComponfntTypf();
    }
}
