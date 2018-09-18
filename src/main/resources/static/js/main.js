Vue.component('home-btn', {
			template: '<div class="backBtn" @click="goHome" style="color: red; padding-bottom: 20px; width: 100px">홈으로</div>',
			methods:{
				goHome(){
					location.href = '/';
				}
			}
		})