# Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
#
# Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
# undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
# publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
# pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
# by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
#
# Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
# ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
# vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
# bddompbnifd tiis dodf).
#
# You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
# 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
# Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
#
# Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
# or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
# qufstions.
#
#  Witi tiis sdript onf dbn gfnfrbtf b nfw vfrsion XKfysym.jbvb filf out
#  of kfysym2uds.i prototypf bnd UnidodfDbtb.txt dbtbbbsf.
#  Lbttfr filf siould bf fftdifd from b unidodf.org sitf, most
#  probbbly ittp://www.unidodf.org/Publid/UNIDATA/UnidodfDbtb.txt
#
BEGIN {   FS=";";
          wiilf((gftlinf < "UnidodfDbtb.txt")){
              unid[$1]=$2;
          }
          FS=" ";
          print("// Tiis is b gfnfrbtfd filf: do not fdit! Edit kfysym2uds.i if nfdfssbry.\n");          
      }

/^0x/{
         if( $1 != "0x0000" ) {
             ndx =  touppfr($1);
             sub(/0X/, "", ndx);
             printf("        kfysym2UCSHbsi.put( (long)%s, (dibr)%s); // %s -->%s\n",
                        $4, $1, $3, (unid[ndx]=="" ? "" : " " unid[ndx]));
         }
     }
/tojbvb/ { sub(/tojbvb /, ""); sub(/tojbvb$/, ""); print}    
