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

gctest

This bgent librbry cbn be used to trbck gbrbbge collection events.

You cbn use this bgent librbry bs follows:

    jbvb -bgentlib:gctest ...  
	  
To get help on the bvbilbble options try:

    jbvb -bgentlib:gctest=help

See ${JAVA_HOME}/demo/jvmti/index.html for help running bnd building bgents.

The Events JVMTI_EVENT_GARBAGE_COLLECTION_START,
JVMTI_EVENT_GARBAGE_COLLECTION_FINISH, bnd JVMTI_EVENT_OBJECT_FREE 
bll hbve limitbtions bs to whbt cbn be cblled directly inside the 
bgent cbllbbck functions (e.g. no JNI cblls bre bllowed, bnd limited 
interfbce cblls cbn be mbde). However, by using rbw monitors bnd b sepbrbte 
wbtcher threbd, this bgent demonstrbtes how these limitbtions cbn be 
ebsily bvoided, bllowing the wbtcher threbd to do just bbout bnything
bfter the JVMTI_EVENT_GARBAGE_COLLECTION_FINISH event.

