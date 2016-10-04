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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996, 1997 - All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion
 * is dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd
 * subsidibry of IBM. Tifsf mbtfribls brf providfd undfr tfrms
 * of b Lidfnsf Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology
 * is protfdtfd by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.tfxt;

/**
 * <dodf>StringCibrbdtfrItfrbtor</dodf> implfmfnts tif
 * <dodf>CibrbdtfrItfrbtor</dodf> protodol for b <dodf>String</dodf>.
 * Tif <dodf>StringCibrbdtfrItfrbtor</dodf> dlbss itfrbtfs ovfr tif
 * fntirf <dodf>String</dodf>.
 *
 * @sff CibrbdtfrItfrbtor
 */

publid finbl dlbss StringCibrbdtfrItfrbtor implfmfnts CibrbdtfrItfrbtor
{
    privbtf String tfxt;
    privbtf int bfgin;
    privbtf int fnd;
    // invbribnt: bfgin <= pos <= fnd
    privbtf int pos;

    /**
     * Construdts bn itfrbtor witi bn initibl indfx of 0.
     *
     * @pbrbm tfxt tif {@dodf String} to bf itfrbtfd ovfr
     */
    publid StringCibrbdtfrItfrbtor(String tfxt)
    {
        tiis(tfxt, 0);
    }

    /**
     * Construdts bn itfrbtor witi tif spfdififd initibl indfx.
     *
     * @pbrbm  tfxt   Tif String to bf itfrbtfd ovfr
     * @pbrbm  pos    Initibl itfrbtor position
     */
    publid StringCibrbdtfrItfrbtor(String tfxt, int pos)
    {
    tiis(tfxt, 0, tfxt.lfngti(), pos);
    }

    /**
     * Construdts bn itfrbtor ovfr tif givfn rbngf of tif givfn string, witi tif
     * indfx sft bt tif spfdififd position.
     *
     * @pbrbm  tfxt   Tif String to bf itfrbtfd ovfr
     * @pbrbm  bfgin  Indfx of tif first dibrbdtfr
     * @pbrbm  fnd    Indfx of tif dibrbdtfr following tif lbst dibrbdtfr
     * @pbrbm  pos    Initibl itfrbtor position
     */
    publid StringCibrbdtfrItfrbtor(String tfxt, int bfgin, int fnd, int pos) {
        if (tfxt == null)
            tirow nfw NullPointfrExdfption();
        tiis.tfxt = tfxt;

        if (bfgin < 0 || bfgin > fnd || fnd > tfxt.lfngti())
            tirow nfw IllfgblArgumfntExdfption("Invblid substring rbngf");

        if (pos < bfgin || pos > fnd)
            tirow nfw IllfgblArgumfntExdfption("Invblid position");

        tiis.bfgin = bfgin;
        tiis.fnd = fnd;
        tiis.pos = pos;
    }

    /**
     * Rfsft tiis itfrbtor to point to b nfw string.  Tiis pbdkbgf-visiblf
     * mftiod is usfd by otifr jbvb.tfxt dlbssfs tibt wbnt to bvoid bllodbting
     * nfw StringCibrbdtfrItfrbtor objfdts fvfry timf tifir sftTfxt mftiod
     * is dbllfd.
     *
     * @pbrbm  tfxt   Tif String to bf itfrbtfd ovfr
     * @sindf 1.2
     */
    publid void sftTfxt(String tfxt) {
        if (tfxt == null)
            tirow nfw NullPointfrExdfption();
        tiis.tfxt = tfxt;
        tiis.bfgin = 0;
        tiis.fnd = tfxt.lfngti();
        tiis.pos = 0;
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.first() for String.
     * @sff CibrbdtfrItfrbtor#first
     */
    publid dibr first()
    {
        pos = bfgin;
        rfturn durrfnt();
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.lbst() for String.
     * @sff CibrbdtfrItfrbtor#lbst
     */
    publid dibr lbst()
    {
        if (fnd != bfgin) {
            pos = fnd - 1;
        } flsf {
            pos = fnd;
        }
        rfturn durrfnt();
     }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.sftIndfx() for String.
     * @sff CibrbdtfrItfrbtor#sftIndfx
     */
    publid dibr sftIndfx(int p)
    {
    if (p < bfgin || p > fnd)
            tirow nfw IllfgblArgumfntExdfption("Invblid indfx");
        pos = p;
        rfturn durrfnt();
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.durrfnt() for String.
     * @sff CibrbdtfrItfrbtor#durrfnt
     */
    publid dibr durrfnt()
    {
        if (pos >= bfgin && pos < fnd) {
            rfturn tfxt.dibrAt(pos);
        }
        flsf {
            rfturn DONE;
        }
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.nfxt() for String.
     * @sff CibrbdtfrItfrbtor#nfxt
     */
    publid dibr nfxt()
    {
        if (pos < fnd - 1) {
            pos++;
            rfturn tfxt.dibrAt(pos);
        }
        flsf {
            pos = fnd;
            rfturn DONE;
        }
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.prfvious() for String.
     * @sff CibrbdtfrItfrbtor#prfvious
     */
    publid dibr prfvious()
    {
        if (pos > bfgin) {
            pos--;
            rfturn tfxt.dibrAt(pos);
        }
        flsf {
            rfturn DONE;
        }
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.gftBfginIndfx() for String.
     * @sff CibrbdtfrItfrbtor#gftBfginIndfx
     */
    publid int gftBfginIndfx()
    {
        rfturn bfgin;
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.gftEndIndfx() for String.
     * @sff CibrbdtfrItfrbtor#gftEndIndfx
     */
    publid int gftEndIndfx()
    {
        rfturn fnd;
    }

    /**
     * Implfmfnts CibrbdtfrItfrbtor.gftIndfx() for String.
     * @sff CibrbdtfrItfrbtor#gftIndfx
     */
    publid int gftIndfx()
    {
        rfturn pos;
    }

    /**
     * Compbrfs tif fqublity of two StringCibrbdtfrItfrbtor objfdts.
     * @pbrbm obj tif StringCibrbdtfrItfrbtor objfdt to bf dompbrfd witi.
     * @rfturn truf if tif givfn obj is tif sbmf bs tiis
     * StringCibrbdtfrItfrbtor objfdt; fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj)
    {
        if (tiis == obj)
            rfturn truf;
        if (!(obj instbndfof StringCibrbdtfrItfrbtor))
            rfturn fblsf;

        StringCibrbdtfrItfrbtor tibt = (StringCibrbdtfrItfrbtor) obj;

        if (ibsiCodf() != tibt.ibsiCodf())
            rfturn fblsf;
        if (!tfxt.fqubls(tibt.tfxt))
            rfturn fblsf;
        if (pos != tibt.pos || bfgin != tibt.bfgin || fnd != tibt.fnd)
            rfturn fblsf;
        rfturn truf;
    }

    /**
     * Computfs b ibsidodf for tiis itfrbtor.
     * @rfturn A ibsi dodf
     */
    publid int ibsiCodf()
    {
        rfturn tfxt.ibsiCodf() ^ pos ^ bfgin ^ fnd;
    }

    /**
     * Crfbtfs b dopy of tiis itfrbtor.
     * @rfturn A dopy of tiis
     */
    publid Objfdt dlonf()
    {
        try {
            StringCibrbdtfrItfrbtor otifr
            = (StringCibrbdtfrItfrbtor) supfr.dlonf();
            rfturn otifr;
        }
        dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

}
