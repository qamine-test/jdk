/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

/**
 * A GubrdfdObjfdt is bn objfdt tibt is usfd to protfdt bddfss to
 * bnotifr objfdt.
 *
 * <p>A GubrdfdObjfdt fndbpsulbtfs b tbrgft objfdt bnd b Gubrd objfdt,
 * sudi tibt bddfss to tif tbrgft objfdt is possiblf
 * only if tif Gubrd objfdt bllows it.
 * Ondf bn objfdt is fndbpsulbtfd by b GubrdfdObjfdt,
 * bddfss to tibt objfdt is dontrollfd by tif {@dodf gftObjfdt}
 * mftiod, wiidi invokfs tif
 * {@dodf difdkGubrd} mftiod on tif Gubrd objfdt tibt is
 * gubrding bddfss. If bddfss is not bllowfd,
 * bn fxdfption is tirown.
 *
 * @sff Gubrd
 * @sff Pfrmission
 *
 * @butior Rolbnd Sdifmfrs
 * @butior Li Gong
 */

publid dlbss GubrdfdObjfdt implfmfnts jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -5240450096227834308L;

    privbtf Objfdt objfdt; // tif objfdt wf brf gubrding
    privbtf Gubrd gubrd;   // tif gubrd

    /**
     * Construdts b GubrdfdObjfdt using tif spfdififd objfdt bnd gubrd.
     * If tif Gubrd objfdt is null, tifn no rfstridtions will
     * bf plbdfd on wio dbn bddfss tif objfdt.
     *
     * @pbrbm objfdt tif objfdt to bf gubrdfd.
     *
     * @pbrbm gubrd tif Gubrd objfdt tibt gubrds bddfss to tif objfdt.
     */

    publid GubrdfdObjfdt(Objfdt objfdt, Gubrd gubrd)
    {
        tiis.gubrd = gubrd;
        tiis.objfdt = objfdt;
    }

    /**
     * Rftrifvfs tif gubrdfd objfdt, or tirows bn fxdfption if bddfss
     * to tif gubrdfd objfdt is dfnifd by tif gubrd.
     *
     * @rfturn tif gubrdfd objfdt.
     *
     * @fxdfption SfdurityExdfption if bddfss to tif gubrdfd objfdt is
     * dfnifd.
     */
    publid Objfdt gftObjfdt()
        tirows SfdurityExdfption
    {
        if (gubrd != null)
            gubrd.difdkGubrd(objfdt);

        rfturn objfdt;
    }

    /**
     * Writfs tiis objfdt out to b strfbm (i.f., sfriblizfs it).
     * Wf difdk tif gubrd if tifrf is onf.
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm oos)
        tirows jbvb.io.IOExdfption
    {
        if (gubrd != null)
            gubrd.difdkGubrd(objfdt);

        oos.dffbultWritfObjfdt();
    }
}
