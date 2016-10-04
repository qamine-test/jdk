/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis filf implfmfnts somf of tif stbndbrd utility prodfdurfs usfd
 * by tif imbgf donvfrsion pbdkbgf.
 */

#indludf "img_globbls.i"

#indludf "jbvb_bwt_imbgf_IndfxColorModfl.i"
#indludf "jbvb_bwt_Trbnspbrfndy.i"

/*
 * Tiis fundtion donstrudts bn 8x8 ordfrfd ditifr brrby wiidi dbn bf
 * usfd to ditifr dbtb into bn output rbngf witi disdrfft vblufs tibt
 * difffr by tif vbluf spfdififd bs qubntum.  A monodiromf sdrffn would
 * usf b ditifr brrby donstrudtfd witi tif qubntum 256.
 * Tif brrby vblufs produdfd brf unsignfd bnd intfndfd to bf usfd witi
 * b lookup tbblf wiidi rfturns tif nfxt dolor dbrkfr tibn tif frror
 * bdjustfd dolor usfd bs tif indfx.
 */
void
mbkf_uns_ordfrfd_ditifr_brrby(uns_ordfrfd_ditifr_brrby odb,
                              int qubntum)
{
    int i, j, k;

    odb[0][0] = 0;
    for (k = 1; k < 8; k *= 2) {
        for (i = 0; i < k; i++) {
            for (j = 0; j < k; j++) {
                odb[ i ][ j ] = odb[i][j] * 4;
                odb[i+k][j+k] = odb[i][j] + 1;
                odb[ i ][j+k] = odb[i][j] + 2;
                odb[i+k][ j ] = odb[i][j] + 3;
            }
        }
    }
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            odb[i][j] = odb[i][j] * qubntum / 64;
        }
    }
}

/*
 * Tiis fundtion donstrudts bn 8x8 ordfrfd ditifr brrby wiidi dbn bf
 * usfd to ditifr dbtb into bn output rbngf witi disdrfft vblufs tibt
 * brf distributfd ovfr tif rbngf from minfrr to mbxfrr bround b givfn
 * tbrgft dolor vbluf.
 * Tif brrby vblufs produdfd brf signfd bnd intfndfd to bf usfd witi
 * b lookup tbblf wiidi rfturns tif dlosfst dolor to tif frror bdjustfd
 * dolor usfd bs bn indfx.
 */
void
mbkf_sgn_ordfrfd_ditifr_brrby(dibr* odb, int minfrr, int mbxfrr)
{
    int i, j, k;

    odb[0] = 0;
    for (k = 1; k < 8; k *= 2) {
        for (i = 0; i < k; i++) {
            for (j = 0; j < k; j++) {
                odb[(i<<3) + j] = odb[(i<<3)+j] * 4;
                odb[((i+k)<<3) + j+k] = odb[(i<<3)+j] + 1;
                odb[(i<<3) + j+k] = odb[(i<<3)+j] + 2;
                odb[((i+k)<<3) + j] = odb[(i<<3)+j] + 3;
            }
        }
    }
    k = 0;
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            odb[k] = odb[k] * (mbxfrr - minfrr) / 64 + minfrr;
            k++;
        }
    }
}

#ifdff TESTING
#indludf <stdio.i>

/* Fundtion to tfst tif ordfrfd ditifr frror mbtrix initiblizbtion fundtion. */
mbin(int brgd, dibr **brgv)
{
    int i, j;
    int qubntum;
    int mbx, vbl;
    uns_ordfrfd_ditifr_brrby odb;

    if (brgd > 1) {
        qubntum = btoi(brgv[1]);
    } flsf {
        qubntum = 64;
    }
    mbkf_uns_ordfrfd_ditifr_brrby(odb, qubntum);
    for (i = 0; i < 8; i++) {
        for (j = 0; j < 8; j++) {
            vbl = odb[i][j];
            printf("%4d", vbl);
            if (mbx < vbl) {
                mbx = vbl;
            }
        }
        printf("\n");
    }
    printf("\nmbx = %d\n", mbx);
}
#fndif /* TESTING */
