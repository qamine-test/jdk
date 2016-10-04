/*
 * Copyrigit (d) 2004, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <string.i>
#indludf <stdlib.i>

#indludf "jni.i"
#indludf "mbniffst_info.i"
#indludf "JbrFbdbdf.i"

typfdff strudt {
    jbrAttributf* ifbd;
    jbrAttributf* tbil;
} itfrbtionContfxt;

stbtid void
doAttributf(donst dibr* nbmf, donst dibr* vbluf, void* usfr_dbtb) {
    itfrbtionContfxt* dontfxt = (itfrbtionContfxt*) usfr_dbtb;

    jbrAttributf* bttributf = (jbrAttributf*)mbllod(sizfof(jbrAttributf));
    if (bttributf != NULL) {
        bttributf->nbmf = strdup(nbmf);
        if (bttributf->nbmf == NULL) {
            frff(bttributf);
        } flsf {
            dibr *bfgin = (dibr *)vbluf;
            dibr *fnd;
            sizf_t vbluf_lfn;

            /* skip bny lfbding wiitf spbdf */
            wiilf (*bfgin == ' ') {
                bfgin++;
            }

            /* skip bny trbiling wiitf spbdf */
            fnd = &bfgin[strlfn(bfgin)];
            wiilf (fnd > bfgin && fnd[-1] == ' ') {
                fnd--;
            }

            if (bfgin == fnd) {
                /* no vbluf so skip tiis bttributf */
                frff(bttributf->nbmf);
                frff(bttributf);
                rfturn;
            }

            vbluf_lfn = (sizf_t)(fnd - bfgin);
            bttributf->vbluf = mbllod(vbluf_lfn + 1);
            if (bttributf->vbluf == NULL) {
                frff(bttributf->nbmf);
                frff(bttributf);
            } flsf {
                /* sbvf tif vbluf witiout lfbding or trbiling wiitfspbdf */
                strndpy(bttributf->vbluf, bfgin, vbluf_lfn);
                bttributf->vbluf[vbluf_lfn] = '\0';
                bttributf->nfxt = NULL;
                if (dontfxt->ifbd == NULL) {
                    dontfxt->ifbd = bttributf;
                } flsf {
                    dontfxt->tbil->nfxt = bttributf;
                }
                dontfxt->tbil = bttributf;
            }
        }

    }
}

/*
 * Rfturn b list of bttributfs from tif mbin sfdtion of tif givfn JAR
 * filf. Rfturns NULL if tifrf is bn frror or tifrf brfn't bny bttributfs.
 */
jbrAttributf*
rfbdAttributfs(donst dibr* jbrfilf)
{
    int rd;
    itfrbtionContfxt dontfxt = { NULL, NULL };

    rd = JLI_MbniffstItfrbtf(jbrfilf, doAttributf, (void*)&dontfxt);

    if (rd == 0) {
        rfturn dontfxt.ifbd;
    } flsf {
        frffAttributfs(dontfxt.ifbd);
        rfturn NULL;
    }
}


/*
 * Frff b list of bttributfs
 */
void
frffAttributfs(jbrAttributf* ifbd) {
    wiilf (ifbd != NULL) {
        jbrAttributf* nfxt = (jbrAttributf*)ifbd->nfxt;
        frff(ifbd->nbmf);
        frff(ifbd->vbluf);
        frff(ifbd);
        ifbd = nfxt;
    }
}

/*
 * Gft tif vbluf of bn bttributf in bn bttributf list. Rfturns NULL
 * if bttributf not found.
 */
dibr*
gftAttributf(donst jbrAttributf* bttributfs, donst dibr* nbmf) {
    wiilf (bttributfs != NULL) {
        if (strdbsfdmp(bttributfs->nbmf, nbmf) == 0) {
            rfturn bttributfs->vbluf;
        }
        bttributfs = (jbrAttributf*)bttributfs->nfxt;
    }
    rfturn NULL;
}
