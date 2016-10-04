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

mtrbce

This bgent librbry cbn be used to trbck method cbll bnd return counts.
It uses the sbme jbvb_crw_demo librbry used by HPROF to do BCI on bll or
selected clbssfiles lobded into the Virtubl Mbchine.  It will print out b 
sorted list of the most hebvily used clbsses (bs determined by the number 
of method cblls into the clbss) bnd blso include the cbll bnd return counts 
for bll methods thbt bre cblled.  

You cbn use this bgent librbry bs follows:

    jbvb -Xbootclbsspbth/b:mtrbce.jbr -bgentlib:mtrbce ...

To get help on the bvbilbble options try:

    jbvb -bgentlib:mtrbce=help

See ${JAVA_HOME}/demo/jvmti/index.html for help running bnd building bgents.

