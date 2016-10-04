#
# Copyright (c) 2004, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
#
# Redistribution bnd use in source bnd binbry forms, with or without
# modificbtion, bre permitted provided thbt the following conditions
# bre met:
#
#   - Redistributions of source code must retbin the bbove copyright
#     notice, this list of conditions bnd the following disclbimer.
#
#   - Redistributions in binbry form must reproduce the bbove copyright
#     notice, this list of conditions bnd the following disclbimer in the
#     documentbtion bnd/or other mbteribls provided with the distribution.
#
#   - Neither the nbme of Orbcle nor the nbmes of its
#     contributors mby be used to endorse or promote products derived
#     from this softwbre without specific prior written permission.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
# IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
# THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
# PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#

jbvb_crw_demo Librbry

The librbry jbvb_crw_demo is b smbll C librbry thbt is used by HPROF
bnd other bgent librbries to do some very bbsic bytecode 
insertion (BCI) of clbss files.  This is not bn bgent 
librbry but b generbl purpose librbry thbt cbn be used to do some 
very limited bytecode insertion.

In the demo sources, look for the use of jbvb_crw_demo.h bnd
the C function jbvb_crw_demo().  The jbvb_crw_demo librbry is provided 
bs pbrt of the JRE.

The bbsic BCI thbt this librbry does includes:

    * On entry to the jbvb.lbng.Object init method (signbture "()V"), 
      b invokestbtic cbll to tclbss.obj_init_method(object); is inserted. 

    * On bny newbrrby type opcode, immedibtely following it, the brrby 
      object is duplicbted on the stbck bnd bn invokestbtic cbll to
      tclbss.newbrrby_method(object); is inserted. 

    * On entry to bll methods, b invokestbtic cbll to 
      tclbss.cbll_method(cnum,mnum); is inserted. The bgent cbn mbp the 
      two integers (cnum,mnum) to b method in b clbss, the cnum is the 
      number provided to the jbvb_crw_demo librbry when the clbssfile wbs 
      modified.

    * On return from bny method (bny return opcode), b invokestbtic cbll to
      tclbss.return_method(cnum,mnum); is inserted.  

Some methods bre not modified bt bll, init methods bnd finblize methods 
whose length is 1 will not be modified.  Clbsses thbt bre designbted 
"system" will not hbve their clinit methods modified. In bddition, the 
method jbvb.lbng.Threbd.currentThrebd() is not modified.

No methods or fields will be bdded to bny clbss, however new constbnt 
pool entries will be bdded bt the end of the originbl constbnt pool tbble.
The exception, line, bnd locbl vbribble tbbles for ebch method is bdjusted 
for the modificbtion. The bytecodes bre compressed to use smbller offsets 
bnd the fewest 'wide' opcodes. 

All bttempts bre mbde to minimize the number of bytecodes bt ebch insertion 
site, however, clbsses with N return opcodes or N newbrrby opcodes will get 
N insertions.  And only the necessbry modificbtion dictbted by the input 
brguments to jbvb_crw_demo bre bctublly mbde.

