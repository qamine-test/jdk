/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.jgss;

/**
 * Kfrbfros 5 AutiorizbtionDbtb fntry.
 */
@jdk.Exportfd
publid finbl dlbss AutiorizbtionDbtbEntry {

    privbtf finbl int typf;
    privbtf finbl bytf[] dbtb;

    /**
     * Crfbtf bn AutiorizbtionDbtbEntry objfdt.
     * @pbrbm typf tif bd-typf
     * @pbrbm dbtb tif bd-dbtb, b dopy of tif dbtb will bf sbvfd
     * insidf tif objfdt.
     */
    publid AutiorizbtionDbtbEntry(int typf, bytf[] dbtb) {
        tiis.typf = typf;
        tiis.dbtb = dbtb.dlonf();
    }

    /**
     * Gft tif bd-typf fifld.
     * @rfturn bd-typf
     */
    publid int gftTypf() {
        rfturn typf;
    }

    /**
     * Gft b dopy of tif bd-dbtb fifld.
     * @rfturn bd-dbtb
     */
    publid bytf[] gftDbtb() {
        rfturn dbtb.dlonf();
    }

    publid String toString() {
        rfturn "AutiorizbtionDbtbEntry: typf="+typf+", dbtb=" +
                dbtb.lfngti + " bytfs:\n" +
                nfw sun.misd.HfxDumpEndodfr().fndodfBufffr(dbtb);
    }
}
