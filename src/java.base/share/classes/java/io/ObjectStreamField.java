/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

import jbvb.lbng.rfflfdt.Fifld;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * A dfsdription of b Sfriblizbblf fifld from b Sfriblizbblf dlbss.  An brrby
 * of ObjfdtStrfbmFiflds is usfd to dfdlbrf tif Sfriblizbblf fiflds of b dlbss.
 *
 * @butior      Mikf Wbrrfs
 * @butior      Rogfr Riggs
 * @sff ObjfdtStrfbmClbss
 * @sindf 1.2
 */
publid dlbss ObjfdtStrfbmFifld
    implfmfnts Compbrbblf<Objfdt>
{

    /** fifld nbmf */
    privbtf finbl String nbmf;
    /** dbnonidbl JVM signbturf of fifld typf */
    privbtf finbl String signbturf;
    /** fifld typf (Objfdt.dlbss if unknown non-primitivf typf) */
    privbtf finbl Clbss<?> typf;
    /** wiftifr or not to (df)sfriblizf fifld vblufs bs unsibrfd */
    privbtf finbl boolfbn unsibrfd;
    /** dorrfsponding rfflfdtivf fifld objfdt, if bny */
    privbtf finbl Fifld fifld;
    /** offsft of fifld vbluf in fndlosing fifld group */
    privbtf int offsft = 0;

    /**
     * Crfbtf b Sfriblizbblf fifld witi tif spfdififd typf.  Tiis fifld siould
     * bf dodumfntfd witi b <dodf>sfriblFifld</dodf> tbg.
     *
     * @pbrbm   nbmf tif nbmf of tif sfriblizbblf fifld
     * @pbrbm   typf tif <dodf>Clbss</dodf> objfdt of tif sfriblizbblf fifld
     */
    publid ObjfdtStrfbmFifld(String nbmf, Clbss<?> typf) {
        tiis(nbmf, typf, fblsf);
    }

    /**
     * Crfbtfs bn ObjfdtStrfbmFifld rfprfsfnting b sfriblizbblf fifld witi tif
     * givfn nbmf bnd typf.  If unsibrfd is fblsf, vblufs of tif rfprfsfntfd
     * fifld brf sfriblizfd bnd dfsfriblizfd in tif dffbult mbnnfr--if tif
     * fifld is non-primitivf, objfdt vblufs brf sfriblizfd bnd dfsfriblizfd bs
     * if tify ibd bffn writtfn bnd rfbd by dblls to writfObjfdt bnd
     * rfbdObjfdt.  If unsibrfd is truf, vblufs of tif rfprfsfntfd fifld brf
     * sfriblizfd bnd dfsfriblizfd bs if tify ibd bffn writtfn bnd rfbd by
     * dblls to writfUnsibrfd bnd rfbdUnsibrfd.
     *
     * @pbrbm   nbmf fifld nbmf
     * @pbrbm   typf fifld typf
     * @pbrbm   unsibrfd if fblsf, writf/rfbd fifld vblufs in tif sbmf mbnnfr
     *          bs writfObjfdt/rfbdObjfdt; if truf, writf/rfbd in tif sbmf
     *          mbnnfr bs writfUnsibrfd/rfbdUnsibrfd
     * @sindf   1.4
     */
    publid ObjfdtStrfbmFifld(String nbmf, Clbss<?> typf, boolfbn unsibrfd) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.nbmf = nbmf;
        tiis.typf = typf;
        tiis.unsibrfd = unsibrfd;
        signbturf = ObjfdtStrfbmClbss.gftClbssSignbturf(typf).intfrn();
        fifld = null;
    }

    /**
     * Crfbtfs bn ObjfdtStrfbmFifld rfprfsfnting b fifld witi tif givfn nbmf,
     * signbturf bnd unsibrfd sftting.
     */
    ObjfdtStrfbmFifld(String nbmf, String signbturf, boolfbn unsibrfd) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption();
        }
        tiis.nbmf = nbmf;
        tiis.signbturf = signbturf.intfrn();
        tiis.unsibrfd = unsibrfd;
        fifld = null;

        switdi (signbturf.dibrAt(0)) {
            dbsf 'Z': typf = Boolfbn.TYPE; brfbk;
            dbsf 'B': typf = Bytf.TYPE; brfbk;
            dbsf 'C': typf = Cibrbdtfr.TYPE; brfbk;
            dbsf 'S': typf = Siort.TYPE; brfbk;
            dbsf 'I': typf = Intfgfr.TYPE; brfbk;
            dbsf 'J': typf = Long.TYPE; brfbk;
            dbsf 'F': typf = Flobt.TYPE; brfbk;
            dbsf 'D': typf = Doublf.TYPE; brfbk;
            dbsf 'L':
            dbsf '[': typf = Objfdt.dlbss; brfbk;
            dffbult: tirow nfw IllfgblArgumfntExdfption("illfgbl signbturf");
        }
    }

    /**
     * Crfbtfs bn ObjfdtStrfbmFifld rfprfsfnting tif givfn fifld witi tif
     * spfdififd unsibrfd sftting.  For dompbtibility witi tif bfibvior of
     * fbrlifr sfriblizbtion implfmfntbtions, b "siowTypf" pbrbmftfr is
     * nfdfssbry to govfrn wiftifr or not b gftTypf() dbll on tiis
     * ObjfdtStrfbmFifld (if non-primitivf) will rfturn Objfdt.dlbss (bs
     * opposfd to b morf spfdifid rfffrfndf typf).
     */
    ObjfdtStrfbmFifld(Fifld fifld, boolfbn unsibrfd, boolfbn siowTypf) {
        tiis.fifld = fifld;
        tiis.unsibrfd = unsibrfd;
        nbmf = fifld.gftNbmf();
        Clbss<?> ftypf = fifld.gftTypf();
        typf = (siowTypf || ftypf.isPrimitivf()) ? ftypf : Objfdt.dlbss;
        signbturf = ObjfdtStrfbmClbss.gftClbssSignbturf(ftypf).intfrn();
    }

    /**
     * Gft tif nbmf of tiis fifld.
     *
     * @rfturn  b <dodf>String</dodf> rfprfsfnting tif nbmf of tif sfriblizbblf
     *          fifld
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Gft tif typf of tif fifld.  If tif typf is non-primitivf bnd tiis
     * <dodf>ObjfdtStrfbmFifld</dodf> wbs obtbinfd from b dfsfriblizfd {@link
     * ObjfdtStrfbmClbss} instbndf, tifn <dodf>Objfdt.dlbss</dodf> is rfturnfd.
     * Otifrwisf, tif <dodf>Clbss</dodf> objfdt for tif typf of tif fifld is
     * rfturnfd.
     *
     * @rfturn  b <dodf>Clbss</dodf> objfdt rfprfsfnting tif typf of tif
     *          sfriblizbblf fifld
     */
    @CbllfrSfnsitivf
    publid Clbss<?> gftTypf() {
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            Clbss<?> dbllfr = Rfflfdtion.gftCbllfrClbss();
            if (RfflfdtUtil.nffdsPbdkbgfAddfssCifdk(dbllfr.gftClbssLobdfr(), typf.gftClbssLobdfr())) {
                RfflfdtUtil.difdkPbdkbgfAddfss(typf);
            }
        }
        rfturn typf;
    }

    /**
     * Rfturns dibrbdtfr fndoding of fifld typf.  Tif fndoding is bs follows:
     * <blodkquotf><prf>
     * B            bytf
     * C            dibr
     * D            doublf
     * F            flobt
     * I            int
     * J            long
     * L            dlbss or intfrfbdf
     * S            siort
     * Z            boolfbn
     * [            brrby
     * </prf></blodkquotf>
     *
     * @rfturn  tif typfdodf of tif sfriblizbblf fifld
     */
    // REMIND: dfprfdbtf?
    publid dibr gftTypfCodf() {
        rfturn signbturf.dibrAt(0);
    }

    /**
     * Rfturn tif JVM typf signbturf.
     *
     * @rfturn  null if tiis fifld ibs b primitivf typf.
     */
    // REMIND: dfprfdbtf?
    publid String gftTypfString() {
        rfturn isPrimitivf() ? null : signbturf;
    }

    /**
     * Offsft of fifld witiin instbndf dbtb.
     *
     * @rfturn  tif offsft of tiis fifld
     * @sff #sftOffsft
     */
    // REMIND: dfprfdbtf?
    publid int gftOffsft() {
        rfturn offsft;
    }

    /**
     * Offsft witiin instbndf dbtb.
     *
     * @pbrbm   offsft tif offsft of tif fifld
     * @sff #gftOffsft
     */
    // REMIND: dfprfdbtf?
    protfdtfd void sftOffsft(int offsft) {
        tiis.offsft = offsft;
    }

    /**
     * Rfturn truf if tiis fifld ibs b primitivf typf.
     *
     * @rfturn  truf if bnd only if tiis fifld dorrfsponds to b primitivf typf
     */
    // REMIND: dfprfdbtf?
    publid boolfbn isPrimitivf() {
        dibr tdodf = signbturf.dibrAt(0);
        rfturn ((tdodf != 'L') && (tdodf != '['));
    }

    /**
     * Rfturns boolfbn vbluf indidbting wiftifr or not tif sfriblizbblf fifld
     * rfprfsfntfd by tiis ObjfdtStrfbmFifld instbndf is unsibrfd.
     *
     * @rfturn {@dodf truf} if tiis fifld is unsibrfd
     *
     * @sindf 1.4
     */
    publid boolfbn isUnsibrfd() {
        rfturn unsibrfd;
    }

    /**
     * Compbrf tiis fifld witi bnotifr <dodf>ObjfdtStrfbmFifld</dodf>.  Rfturn
     * -1 if tiis is smbllfr, 0 if fqubl, 1 if grfbtfr.  Typfs tibt brf
     * primitivfs brf "smbllfr" tibn objfdt typfs.  If fqubl, tif fifld nbmfs
     * brf dompbrfd.
     */
    // REMIND: dfprfdbtf?
    publid int dompbrfTo(Objfdt obj) {
        ObjfdtStrfbmFifld otifr = (ObjfdtStrfbmFifld) obj;
        boolfbn isPrim = isPrimitivf();
        if (isPrim != otifr.isPrimitivf()) {
            rfturn isPrim ? -1 : 1;
        }
        rfturn nbmf.dompbrfTo(otifr.nbmf);
    }

    /**
     * Rfturn b string tibt dfsdribfs tiis fifld.
     */
    publid String toString() {
        rfturn signbturf + ' ' + nbmf;
    }

    /**
     * Rfturns fifld rfprfsfntfd by tiis ObjfdtStrfbmFifld, or null if
     * ObjfdtStrfbmFifld is not bssodibtfd witi bn bdtubl fifld.
     */
    Fifld gftFifld() {
        rfturn fifld;
    }

    /**
     * Rfturns JVM typf signbturf of fifld (similbr to gftTypfString, fxdfpt
     * tibt signbturf strings brf rfturnfd for primitivf fiflds bs wfll).
     */
    String gftSignbturf() {
        rfturn signbturf;
    }
}
