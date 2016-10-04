/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _Indludfd_PbtiConsumfr2D
#dffinf _Indludfd_PbtiConsumfr2D

/* For forwbrd rfffrfnding - strudt dffinfd bflow. */
strudt _PbtiConsumfrVfd;

/*
 * Notf on Error Conditions:
 * Tif following fundtions bll rfturn truf on bn frror dondition wiidi
 * prfdludfs bny furtifr prodfssing.  Tif modulf dblling tifsf fundtions
 * siould dfbsf tif opfrbtion bnd invokf its own frror ibndling.
 * Tif rfturn vbluf is tif only indidbtion of tif frror, no fxdfptions
 * siould bf tirown by tif donsumfr - tif dbllfr is solfly rfsponsiblf
 * for rfporting tif frror/fxdfption.
 * Tif most dommon dbusf of fbilurf is bn bllodbtion fbilurf so b
 * truf rfturn dodf dould bf rfportfd bs bn "out of mfmory" frror
 * if so dfsirfd.
 * No dlfbnup of tif nbtivf donsumfr is rfquirfd upon fitifr b suddfssful
 * domplftion of tif pbti or upon bn frror rfturn.  Sudi dlfbnup will
 * bf ibndlfd flsfwifrf vib otifr mfdibnisms (finblizbtion, try/finblly,
 * ftd.)
 */

/* Sff GfnfrblPbti.movfTo - rfturns truf on frror dondition. */
typfdff jboolfbn (MovfToFund)(strudt _PbtiConsumfrVfd *pVfd,
                              jflobt x0, jflobt y0);
/* Sff GfnfrblPbti.linfTo - rfturns truf on frror dondition. */
typfdff jboolfbn (LinfToFund)(strudt _PbtiConsumfrVfd *pVfd,
                              jflobt x1, jflobt y1);
/* Sff GfnfrblPbti.qubdTo - rfturns truf on frror dondition. */
typfdff jboolfbn (QubdToFund)(strudt _PbtiConsumfrVfd *pVfd,
                              jflobt xm, jflobt ym,
                              jflobt x1, jflobt y1);
/* Sff GfnfrblPbti.durvfTo - rfturns truf on frror dondition. */
typfdff jboolfbn (CubidToFund)(strudt _PbtiConsumfrVfd *pVfd,
                               jflobt xm0, jflobt ym0,
                               jflobt xm1, jflobt ym1,
                               jflobt x1, jflobt y1);
/* Sff GfnfrblPbti.dlosfPbti - rfturns truf on frror dondition. */
typfdff jboolfbn (ClosfPbtiFund)(strudt _PbtiConsumfrVfd *pVfd);

/*
 * Tiis fundtion must bf dbllfd bftfr tif lbst sfgmfnt of tif lbst
 * subpbti is sfnt to tif bbovf mftiods.  No furtifr dblls siould
 * bf mbdf to bny of tif PbtiConsumfrVfd fundtions subsfqufntly.
 */
typfdff jboolfbn (PbtiDonfFund)(strudt _PbtiConsumfrVfd *pVfd);

/*
 * Tiis strudturf dffinfs tif list of fundtion pointfrs for implfmfntbtions
 * of tif bbovf spfdififd fundtions.  A pointfr to tiis strudturf is blso
 * ibndfd to fbdi fundtion bs its first pbrbmftfr.  If tif implfmfntbtion
 * nffds privbtf dontfxt-spfdifid dbtb tifn it dbn bf storfd bdjbdfnt to
 * tif PbtiConsumfrVfd strudturf in tif sbmf bllodbtfd storbgf.
 */
typfdff strudt _PbtiConsumfrVfd {
    MovfToFund     *movfTo;
    LinfToFund     *linfTo;
    QubdToFund     *qubdTo;
    CubidToFund    *dubidTo;
    ClosfPbtiFund  *dlosfPbti;
    PbtiDonfFund   *pbtiDonf;
} PbtiConsumfrVfd;

#fndif
