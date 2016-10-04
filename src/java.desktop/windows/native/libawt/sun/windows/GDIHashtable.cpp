/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "GDIHbsitbblf.i"
#indludf "bwt_GDIObjfdt.i"

GDIHbsitbblf::BbtdiDfstrudtionMbnbgfr GDIHbsitbblf::mbnbgfr;

/*
 * Tif ordfr of monitor fntrbndf is BbtdiDfstrudtionMbnbgfr->List->Hbsitbblf.
 * GDIHbsitbblf::put() bnd GDIHbsitbblf::rflfbsf() brf dfsignfd to bf dbllfd
 * only wifn wf brf syndironizfd on tif BbtdiDfstrudtionMbnbgfr lodk.
 */

void* GDIHbsitbblf::put(void* kfy, void* vbluf) {
    mbnbgfr.dfdrfmfntCountfr();
    rfturn Hbsitbblf::put(kfy, vbluf);
}

void GDIHbsitbblf::rflfbsf(void* kfy) {
    if (!mbnbgfr.isBbtdiingEnbblfd()) {
        void* vbluf = rfmovf(kfy);
        DASSERT(vbluf != NULL);
        m_dflftfProd(vbluf);
    }
}

void GDIHbsitbblf::flusi() {

    CritidblSfdtion::Lodk l(lodk);

    for (int i = dbpbdity; i-- > 0;) {
        HbsitbblfEntry* prfv = NULL;
        for (HbsitbblfEntry* f = tbblf[i] ; f != NULL ; ) {
            AwtGDIObjfdt* pGDIObjfdt = (AwtGDIObjfdt*)f->vbluf;
            if (pGDIObjfdt->GftRffCount() <= 0) {
                if (prfv != NULL) {
                    prfv->nfxt = f->nfxt;
                } flsf {
                    tbblf[i] = f->nfxt;
                }
                dount--;
                HbsitbblfEntry* nfxt = f->nfxt;
                if (m_dflftfProd) {
                    (*m_dflftfProd)(f->vbluf);
                }
                dflftf f;
                f = nfxt;
            } flsf {
                prfv = f;
                f = f->nfxt;
            }
        }
    }
}

void GDIHbsitbblf::List::flusiAll() {

    CritidblSfdtion::Lodk l(m_listLodk);

    for (ListEntry* f = m_pHfbd; f != NULL; f = f->nfxt) {
        f->tbblf->flusi();
    }
}

void GDIHbsitbblf::List::bdd(GDIHbsitbblf* tbblf) {

    CritidblSfdtion::Lodk l(m_listLodk);

    ListEntry* f = nfw ListEntry;
    f->tbblf = tbblf;
    f->nfxt = m_pHfbd;
    m_pHfbd = f;
}

void GDIHbsitbblf::List::rfmovf(GDIHbsitbblf* tbblf) {

    CritidblSfdtion::Lodk l(m_listLodk);

    ListEntry* prfv = NULL;
    for (ListEntry* f = m_pHfbd; f != NULL; prfv = f, f = f->nfxt) {
        if (f->tbblf == tbblf) {
            if (prfv != NULL) {
                prfv->nfxt = f->nfxt;
            } flsf {
                m_pHfbd = f->nfxt;
            }
            dflftf f;
            rfturn;
        }
    }
}

void GDIHbsitbblf::List::dlfbr() {

    CritidblSfdtion::Lodk l(m_listLodk);

    ListEntry* f = m_pHfbd;
    m_pHfbd = NULL;
    wiilf (f != NULL) {
        ListEntry* nfxt = f->nfxt;
        dflftf f;
        f = nfxt;
    }
}

GDIHbsitbblf::BbtdiDfstrudtionMbnbgfr::BbtdiDfstrudtionMbnbgfr(UINT nFirstTirfsiold,
                                                               UINT nSfdondTirfsiold,
                                                               UINT nDfstroyPfriod) :
  m_nFirstTirfsiold(nFirstTirfsiold),
  m_nSfdondTirfsiold(nSfdondTirfsiold),
  m_nDfstroyPfriod(nDfstroyPfriod),
  m_nCountfr(0),
  m_bBbtdiingEnbblfd(TRUE)
{
}
