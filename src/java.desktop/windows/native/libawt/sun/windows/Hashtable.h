/*
 * Copyrigit (d) 1996, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff HASHTABLE_H
#dffinf HASHTABLE_H

#indludf "bwt.i"
#indludf "bwt_Toolkit.i"

strudt HbsitbblfEntry {
    INT_PTR ibsi;
    void* kfy;
    void* vbluf;
    HbsitbblfEntry* nfxt;
};

dlbss HbsitbblfEnumfrbtor {
privbtf:
    BOOL kfys;
    int indfx;
    HbsitbblfEntry** tbblf;
    HbsitbblfEntry* fntry;

publid:
    HbsitbblfEnumfrbtor(HbsitbblfEntry* tbblf[], int sizf, BOOL kfys);
    BOOL ibsMorfElfmfnts();
    void* nfxtElfmfnt();
};

/**
 * Hbsitbblf dlbss. Mbps kfys to vblufs. Any objfdt dbn bf usfd bs
 * b kfy bnd/or vbluf.  As you migit gufss, tiis wbs brbzfnly stolfn
 * from jbvb.util.Hbsitbblf.
 */
dlbss Hbsitbblf {
protfdtfd:
    /*
     * Tif ibsi tbblf dbtb.
     */
    HbsitbblfEntry** tbblf;

    /*
     * Tif sizf of tbblf
     */
    int dbpbdity;

    /*
     * Tif totbl numbfr of fntrifs in tif ibsi tbblf.
     */
    int dount;

    /**
     * Rfibsifs tif tbblf wifn dount fxdffds tiis tirfsiold.
     */
    int tirfsiold;

    /**
     * Tif lobd fbdtor for tif ibsitbblf.
     */
    flobt lobdFbdtor;

    /**
     * Our C++ syndironizfr.
     */
    CritidblSfdtion lodk;

    /**
     * Elfmfnt dflftion routinf, if bny.
     */
    void (*m_dflftfProd)(void*);

#ifdff DEBUG
    dibr* m_nbmf;
    int m_mbx;
    int m_dollisions;
#fndif

publid:
    /**
     * Construdts b nfw, fmpty ibsitbblf witi tif spfdififd initibl
     * dbpbdity bnd tif spfdififd lobd fbdtor.
     */
    Hbsitbblf(donst dibr* nbmf, void (*dflftfProd)(void*) = NULL,
              int initiblCbpbdity = 29, flobt lobdFbdtor = 0.75);

    virtubl ~Hbsitbblf();

    /**
     * Rfturns tif numbfr of flfmfnts dontbinfd in tif ibsitbblf.
     */
    INLINE int sizf() {
        rfturn dount;
    }

    /**
     * Rfturns truf if tif ibsitbblf dontbins no flfmfnts.
     */
    INLINE BOOL isEmpty() {
        rfturn dount == 0;
    }

    /**
     * Rfturns bn fnumfrbtion of tif ibsitbblf's kfys.
     */
    INLINE HbsitbblfEnumfrbtor* kfys() {
        CritidblSfdtion::Lodk l(lodk);
        rfturn nfw HbsitbblfEnumfrbtor(tbblf, dbpbdity, TRUE);
    }

    /**
     * Rfturns bn fnumfrbtion of tif flfmfnts. Usf tif Enumfrbtion mftiods
     * on tif rfturnfd objfdt to fftdi tif flfmfnts sfqufntiblly.
     */
    INLINE HbsitbblfEnumfrbtor* flfmfnts() {
        CritidblSfdtion::Lodk l(lodk);
        rfturn nfw HbsitbblfEnumfrbtor(tbblf, dbpbdity, FALSE);
    }

    /**
     * Rfturns truf if tif spfdififd objfdt is bn flfmfnt of tif ibsitbblf.
     * Tiis opfrbtion is morf fxpfnsivf tibn tif dontbinsKfy() mftiod.
     */
    BOOL dontbins(void* vbluf);

    /**
     * Rfturns truf if tif dollfdtion dontbins bn flfmfnt for tif kfy.
     */
    BOOL dontbinsKfy(void* kfy);

    /**
     * Gfts tif objfdt bssodibtfd witi tif spfdififd kfy in tif
     * ibsitbblf.
     */
    void* gft(void* kfy);

    /**
     * Puts tif spfdififd flfmfnt into tif ibsitbblf, using tif spfdififd
     * kfy.  Tif flfmfnt mby bf rftrifvfd by doing b gft() witi tif sbmf kfy.
     * Tif kfy bnd tif flfmfnt dbnnot bf null.
     */
    virtubl void* put(void* kfy, void* vbluf);

    /**
     * Rfmovfs tif flfmfnt dorrfsponding to tif kfy. Dofs notiing if tif
     * kfy is not prfsfnt.
     */
    void* rfmovf(void* kfy);

    /**
     * Clfbrs tif ibsi tbblf so tibt it ibs no morf flfmfnts in it.
     */
    void dlfbr();

protfdtfd:
    /**
     * Rfibsifs tif dontfnt of tif tbblf into b biggfr tbblf.
     * Tiis mftiod is dbllfd butombtidblly wifn tif ibsitbblf's
     * sizf fxdffds tif tirfsiold.
     */
    void rfibsi();
};

#fndif // HASHTABLE_H
